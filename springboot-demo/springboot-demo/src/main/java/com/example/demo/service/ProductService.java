package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.Product.ProductStatus;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // ─── Basic CRUD ───────────────────────────────────────────────────────────

    @Transactional
    public Product create(Product product) {
        log.debug("Creating product: {}", product.getName());
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    @Transactional
    public Product update(Long id, Product updated) {
        Product existing = findById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setStock(updated.getStock());
        existing.setStatus(updated.getStatus());
        return productRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    // ─── JPQL-backed queries ──────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<Product> findLowStock(int threshold) {
        log.debug("Finding products with stock < {}", threshold);
        return productRepository.findLowStockProducts(threshold);
    }

    @Transactional(readOnly = true)
    public List<Product> findInPriceRange(BigDecimal min, BigDecimal max, ProductStatus status) {
        return productRepository.findByPriceRangeAndStatus(min, max, status);
    }

    @Transactional(readOnly = true)
    public Map<String, Double> averagePriceByStatus() {
        return productRepository.averagePriceByStatus()
                .stream()
                .collect(Collectors.toMap(
                        row -> row[0].toString(),
                        row -> ((Number) row[1]).doubleValue()
                ));
    }

    // ─── Native-query-backed methods ──────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<Product> search(String keyword) {
        return productRepository.searchByKeyword(keyword);
    }

    @Transactional(readOnly = true)
    public List<Product> topExpensive(int n) {
        return productRepository.findTopNExpensiveActive(n);
    }

    @Transactional(readOnly = true)
    public BigDecimal totalStockValue() {
        return productRepository.totalStockValue();
    }

    // ─── Bulk / scheduled operations ─────────────────────────────────────────

    /**
     * Called by the scheduler: marks every product with stock=0 as OUT_OF_STOCK.
     * Returns the number of rows updated.
     */
    @Transactional
    public int syncOutOfStockStatus() {
        int updated = productRepository.markOutOfStock();
        log.info("Marked {} product(s) as OUT_OF_STOCK", updated);
        return updated;
    }

    /**
     * Called by the scheduler: deactivates products older than {@code days} days.
     */
    @Transactional
    public int deactivateStaleProducts(int days) {
        int updated = productRepository.deactivateOldProducts(days);
        log.info("Deactivated {} stale product(s) (older than {} days)", updated, days);
        return updated;
    }
}
