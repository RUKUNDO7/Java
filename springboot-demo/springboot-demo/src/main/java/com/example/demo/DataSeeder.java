package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.entity.Product.ProductStatus;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Seeds the in-memory H2 database with sample products on startup.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        if (productRepository.count() > 0) return;

        List<Product> products = List.of(
            Product.builder().name("Laptop Pro").description("High-end developer laptop")
                    .price(new BigDecimal("1499.99")).stock(10).status(ProductStatus.ACTIVE).build(),
            Product.builder().name("Wireless Mouse").description("Ergonomic wireless mouse")
                    .price(new BigDecimal("39.99")).stock(3).status(ProductStatus.ACTIVE).build(),
            Product.builder().name("Mechanical Keyboard").description("RGB mechanical keyboard")
                    .price(new BigDecimal("129.99")).stock(0).status(ProductStatus.ACTIVE).build(),
            Product.builder().name("USB-C Hub").description("7-in-1 USB-C hub")
                    .price(new BigDecimal("49.99")).stock(25).status(ProductStatus.ACTIVE).build(),
            Product.builder().name("4K Monitor").description("27-inch 4K display")
                    .price(new BigDecimal("599.99")).stock(7).status(ProductStatus.ACTIVE).build(),
            Product.builder().name("Webcam HD").description("1080p webcam with mic")
                    .price(new BigDecimal("79.99")).stock(2).status(ProductStatus.ACTIVE).build(),
            Product.builder().name("Old Headset").description("Discontinued model")
                    .price(new BigDecimal("19.99")).stock(1).status(ProductStatus.INACTIVE).build()
        );

        productRepository.saveAll(products);
        log.info("Seeded {} products into the database.", products.size());
    }
}
