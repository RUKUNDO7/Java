package com.example.demo.controller;

import com.example.demo.entity.AuditLog;
import com.example.demo.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditLogRepository auditLogRepository;

    /** All audit entries (most recent first). */
    @GetMapping
    public List<AuditLog> all(@RequestParam(defaultValue = "20") int limit) {
        return auditLogRepository.findRecentEntries(PageRequest.of(0, limit));
    }

    /** Audit entries for a specific task name. */
    @GetMapping("/task/{taskName}")
    public List<AuditLog> byTask(@PathVariable String taskName) {
        return auditLogRepository.findByTaskNameOrderByExecutedAtDesc(taskName);
    }
}
