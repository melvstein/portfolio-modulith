package dev.melvstein.spring_portfolio_modulith.auth.api.kafka.event;

import com.fasterxml.jackson.databind.JsonNode;
import dev.melvstein.spring_portfolio_modulith.audit.api.enm.AuditActionEnum;
import dev.melvstein.spring_portfolio_modulith.audit.api.enm.AuditEntityTpeEnum;
import dev.melvstein.spring_portfolio_modulith.audit.api.enm.AuditModuleEnum;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AuditLogEvent(

        AuditModuleEnum module,
        AuditActionEnum action,
        AuditEntityTpeEnum entityType,
        UUID entityId,
        UUID actorId,
        String description,
        String ipAddress,
        String userAgent,
        JsonNode before,
        JsonNode after) {
}
