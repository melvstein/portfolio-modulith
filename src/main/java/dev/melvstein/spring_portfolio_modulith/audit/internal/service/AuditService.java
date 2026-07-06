package dev.melvstein.spring_portfolio_modulith.audit.internal.service;

import dev.melvstein.spring_portfolio_modulith.audit.api.entity.AuditLog;
import dev.melvstein.spring_portfolio_modulith.audit.internal.repository.AuditRepository;
import dev.melvstein.spring_portfolio_modulith.auth.api.kafka.event.UserRegisteredEvent;
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

    public void createAuditLog(UserRegisteredEvent event) {
        AuditLog auditLog = AuditLog.builder()
                .module(event.auditLog().module())
                .action(event.auditLog().action())
                .entityType(event.auditLog().entityType())
                .entityId(event.auditLog().entityId())
                .actorId(event.auditLog().actorId())
                .description(event.auditLog().description())
                .ipAddress(event.auditLog().ipAddress())
                .userAgent(event.auditLog().userAgent())
                .build();

        save(auditLog);
    }
}
