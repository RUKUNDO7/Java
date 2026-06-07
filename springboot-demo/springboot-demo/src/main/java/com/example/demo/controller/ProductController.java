package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Product.ProductStatus;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ─── CRUD ─────────────────────────────────────────────────────────────────

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(productService.create(product));
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ─── Query endpoints ──────────────────────────────────────────────────────

    /** JPQL – products with stock below the given threshold */
    @GetMapping("/low-stock")
    public List<Product> lowStock(@RequestParam(defaultValue = "5") int threshold) {
        return productService.findLowStock(threshold);
    }

    /** JPQL – price range + status filter */
    @GetMapping("/price-range")
    public List<Product> priceRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max,
            @RequestParam(defaultValue = "ACTIVE") ProductStatus status) {
        return productService.findInPriceRange(min, max, status);
    }

    /** JPQL aggregate – average price grouped by status */
    @GetMapping("/stats/avg-price")
    public Map<String, Double> avgPriceByStatus() {
        return productService.averagePriceByStatus();
    }

    /** Native – full-text keyword search */
    @GetMapping("/search")
    public List<Product> search(@RequestParam String q) {
        return productService.search(q);
    }

    /** Native – top N most expensive active products */
    @GetMapping("/top-expensive")
    public List<Product> topExpensive(@RequestParam(defaultValue = "5") int n) {
        return productService.topExpensive(n);
    }

    /** Native aggregate – total inventory value */
    @GetMapping("/stats/total-value")
    public Map<String, BigDecimal> totalValue() {
        return Map.of("totalStockValue", productService.totalStockValue());
    }
}
