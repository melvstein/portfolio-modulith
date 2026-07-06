package dev.melvstein.spring_portfolio_modulith.audit.internal.service;

import dev.melvstein.spring_portfolio_modulith.audit.api.entity.AuditLog;
import dev.melvstein.spring_portfolio_modulith.audit.internal.repository.AuditRepository;
import dev.melvstein.spring_portfolio_modulith.common.api.kafka.event.AuditLogEvent;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    private final AuditRepository auditRepository;

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public AuditLog save(AuditLog auditLog) {
        return auditRepository.save(auditLog);
    }

    public void createAuditLog(AuditLogEvent event) {
        AuditLog auditLog = AuditLog.builder()
                .module(event.module())
                .action(event.action())
                .entityType(event.entityType())
                .entityId(event.entityId())
                .actorId(event.actorId())
                .description(event.description())
                .ipAddress(event.ipAddress())
                .userAgent(event.userAgent())
                .before(event.before())
                .after(event.after())
                .build();

        save(auditLog);
    }
}
