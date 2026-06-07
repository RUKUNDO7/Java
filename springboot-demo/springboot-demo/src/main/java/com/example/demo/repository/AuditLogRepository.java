package com.example.demo.repository;

import com.example.demo.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByTaskNameOrderByExecutedAtDesc(String taskName);

    /** JPQL: most recent N audit entries across all tasks. */
    @Query("SELECT a FROM AuditLog a ORDER BY a.executedAt DESC")
    List<AuditLog> findRecentEntries(org.springframework.data.domain.Pageable pageable);
}
