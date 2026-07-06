package dev.melvstein.spring_portfolio_modulith.auth.api.kafka.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserRegisteredEvent(

        UUID authUserId,
        AuditLogEvent auditLog
) {
}
