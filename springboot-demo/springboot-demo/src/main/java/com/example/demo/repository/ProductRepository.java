package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.Product.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // ─── Derived query (Spring Data generates SQL automatically) ─────────────

    List<Product> findByStatus(ProductStatus status);

    Optional<Product> findByName(String name);

    List<Product> findByPriceLessThanEqual(BigDecimal maxPrice);


    // ─── JPQL queries ─────────────────────────────────────────────────────────

    /**
     * JPQL: uses entity/field names, database-agnostic.
     * Finds all products where stock is below a given threshold.
     */
    @Query("SELECT p FROM Product p WHERE p.stock < :threshold ORDER BY p.stock ASC")
    List<Product> findLowStockProducts(@Param("threshold") int threshold);

    /**
     * JPQL: fetches products in a price range with a specific status.
     */
    @Query("SELECT p FROM Product p " +
           "WHERE p.price BETWEEN :minPrice AND :maxPrice " +
           "AND p.status = :status")
    List<Product> findByPriceRangeAndStatus(
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("status") ProductStatus status);

    /**
     * JPQL aggregate: average price per status.
     * Returns Object[] rows: [status, avgPrice].
     */
    @Query("SELECT p.status, AVG(p.price) FROM Product p GROUP BY p.status")
    List<Object[]> averagePriceByStatus();


    // ─── Native SQL queries ───────────────────────────────────────────────────

    /**
     * Native query: raw SQL, dialect-specific.
     * Searches name and description using LIKE (H2 / most RDBMS).
     */
    @Query(value = "SELECT * FROM products " +
                   "WHERE LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                   "   OR LOWER(description) LIKE LOWER(CONCAT('%', :keyword, '%'))",
           nativeQuery = true)
    List<Product> searchByKeyword(@Param("keyword") String keyword);

    /**
     * Native query with pagination: top N most expensive active products.
     */
    @Query(value = "SELECT * FROM products WHERE status = 'ACTIVE' " +
                   "ORDER BY price DESC LIMIT :n",
           nativeQuery = true)
    List<Product> findTopNExpensiveActive(@Param("n") int n);

    /**
     * Native query: total stock value (price * stock) across all products.
     */
    @Query(value = "SELECT COALESCE(SUM(price * stock), 0) FROM products",
           nativeQuery = true)
    BigDecimal totalStockValue();


    // ─── Modifying queries ────────────────────────────────────────────────────

    /**
     * JPQL bulk update: mark all zero-stock products as OUT_OF_STOCK.
     * @Modifying required for UPDATE/DELETE JPQL statements.
     */
    @Modifying
    @Query("UPDATE Product p SET p.status = 'OUT_OF_STOCK' WHERE p.stock = 0")
    int markOutOfStock();

    /**
     * Native bulk update: deactivate products older than the given days.
     */
    @Modifying
    @Query(value = "UPDATE products SET status = 'INACTIVE' " +
                   "WHERE created_at < DATEADD('DAY', -:days, NOW()) " +
                   "AND status = 'ACTIVE'",
           nativeQuery = true)
    int deactivateOldProducts(@Param("days") int days);
}
