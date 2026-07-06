package dev.melvstein.spring_portfolio_modulith.audit.api.kafka;

import dev.melvstein.spring_portfolio_modulith.audit.internal.service.AuditService;
import dev.melvstein.spring_portfolio_modulith.auth.api.kafka.event.AuditLogEvent;
import dev.melvstein.spring_portfolio_modulith.auth.api.kafka.event.UserRegisteredEvent;
import dev.melvstein.spring_portfolio_modulith.common.api.kafka.KafkaGroups;
import dev.melvstein.spring_portfolio_modulith.common.api.kafka.KafkaTopics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class AuditListener {

    private final AuditService auditService;

    public AuditListener(AuditService auditService) {
        this.auditService = auditService;
    }

    @Transactional
    @KafkaListener(
            topics = KafkaTopics.USER_REGISTERED,
            groupId = KafkaGroups.USERS
    )
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        log.info("[AuditLog] Received handleUserRegisteredEvent {}", event);

        auditService.createAuditLog(event.auditLog());
    }

    @Transactional
    @KafkaListener(
            topics = KafkaTopics.USER_UPDATED,
            groupId = KafkaGroups.USERS
    )
    public void handleUserUpdatedEvent(AuditLogEvent event) {
        log.info("[AuditLog] Received handleUserUpdatedEvent {}", event);

        auditService.createAuditLog(event);
    }
}
