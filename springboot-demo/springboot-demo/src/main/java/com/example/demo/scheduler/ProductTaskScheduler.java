package com.example.demo.scheduler;

import com.example.demo.entity.AuditLog;
import com.example.demo.entity.Product;
import com.example.demo.repository.AuditLogRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates the three main @Scheduled modes:
 *   1. fixedRate   – fire every N ms, counting from when the previous run STARTED
 *   2. fixedDelay  – fire every N ms, counting from when the previous run ENDED
 *   3. cron        – fire on a cron expression (seconds minutes hours day month weekday)
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ProductTaskScheduler {

    private final ProductService productService;
    private final AuditLogRepository auditLogRepository;

    /** Simple counter to track how many times each task has fired. */
    private final AtomicInteger stockCheckCount = new AtomicInteger(0);

    // ─── 1. Fixed-rate task ───────────────────────────────────────────────────
    //
    // Fires every 30 seconds regardless of how long the task body takes.
    // Good for: health checks, metrics collection, polling.
    //
    @Scheduled(fixedRate = 30_000, initialDelay = 5_000)
    public void checkLowStockProducts() {
        int run = stockCheckCount.incrementAndGet();
        log.info("[fixedRate] Low-stock check #{}", run);

        List<Product> lowStock = productService.findLowStock(5);

        String msg = lowStock.isEmpty()
                ? "All products are well-stocked."
                : lowStock.size() + " product(s) have stock < 5: " +
                  lowStock.stream().map(Product::getName).toList();

        log.info("[fixedRate] {}", msg);
        saveAuditLog("LOW_STOCK_CHECK", msg);
    }


    // ─── 2. Fixed-delay task ──────────────────────────────────────────────────
    //
    // Waits 60 seconds after the previous execution COMPLETES before firing again.
    // Good for: tasks that must not overlap with their own next run.
    //
    @Scheduled(fixedDelay = 60_000, initialDelay = 10_000)
    public void syncOutOfStockStatus() {
        log.info("[fixedDelay] Syncing out-of-stock statuses...");

        int updated = productService.syncOutOfStockStatus();
        String msg = "Marked " + updated + " product(s) as OUT_OF_STOCK.";

        log.info("[fixedDelay] {}", msg);
        saveAuditLog("OUT_OF_STOCK_SYNC", msg);
    }


    // ─── 3. Cron task ─────────────────────────────────────────────────────────
    //
    // Cron format: second  minute  hour  day-of-month  month  day-of-week
    // "0 0 * * * *"  → top of every hour
    // "0 */5 * * * *"→ every 5 minutes
    //
    // This task runs at the top of every minute (for demo purposes).
    // In production you'd use something like "0 0 2 * * *" (2 AM daily).
    //
    @Scheduled(cron = "0 * * * * *")          // every minute, at second 0
    public void generateInventoryReport() {
        log.info("[cron] Generating inventory report...");

        var totalValue = productService.totalStockValue();
        var avgByStatus = productService.averagePriceByStatus();
        var topProducts = productService.topExpensive(3);

        String msg = String.format(
                "Inventory report | Total value: %.2f | Avg prices by status: %s | Top-3: %s",
                totalValue,
                avgByStatus,
                topProducts.stream().map(Product::getName).toList()
        );

        log.info("[cron] {}", msg);
        saveAuditLog("INVENTORY_REPORT", msg);
    }


    // ─── 4. Cron task with zone ───────────────────────────────────────────────
    //
    // Runs at 03:00 AM Amsterdam time every day.
    // Shows how to pin a cron task to a specific timezone.
    //
    @Scheduled(cron = "0 0 3 * * *", zone = "Europe/Amsterdam")
    public void deactivateStaleProducts() {
        log.info("[cron/zone] Deactivating stale products (> 365 days old)...");

        int updated = productService.deactivateStaleProducts(365);
        String msg = "Deactivated " + updated + " product(s) inactive for over a year.";

        log.info("[cron/zone] {}", msg);
        saveAuditLog("STALE_PRODUCT_CLEANUP", msg);
    }


    // ─── Helper ───────────────────────────────────────────────────────────────

    private void saveAuditLog(String taskName, String message) {
        auditLogRepository.save(AuditLog.builder()
                .taskName(taskName)
                .message(message)
                .executedAt(LocalDateTime.now())
                .build());
    }
}
