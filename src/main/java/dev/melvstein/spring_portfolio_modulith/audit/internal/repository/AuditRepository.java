package dev.melvstein.spring_portfolio_modulith.audit.internal.repository;

import dev.melvstein.spring_portfolio_modulith.audit.api.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditRepository extends JpaRepository<AuditLog, UUID> {
}
