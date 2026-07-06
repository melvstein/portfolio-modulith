package dev.melvstein.spring_portfolio_modulith.auth.api.kafka.event;

import dev.melvstein.spring_portfolio_modulith.audit.api.enm.AuditActionEnum;
import dev.melvstein.spring_portfolio_modulith.audit.api.enm.AuditEntityTpeEnum;
import dev.melvstein.spring_portfolio_modulith.audit.api.enm.AuditModuleEnum;
import lombok.Builder;

import java.net.InetAddress;
import java.util.UUID;

@Builder
public record UserRegisteredEvent(

        UUID authUserId,
        AuditLog auditLog
) {

    @Builder
    public record AuditLog(

            AuditModuleEnum module,
            AuditActionEnum action,
            AuditEntityTpeEnum entityType,
            UUID entityId,
            UUID actorId,
            String description,
            String ipAddress,
            String userAgent
    ) {}
}
