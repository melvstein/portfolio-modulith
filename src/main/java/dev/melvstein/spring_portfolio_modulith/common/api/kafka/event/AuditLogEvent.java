package dev.melvstein.spring_portfolio_modulith.common.api.kafka.event;

import com.fasterxml.jackson.databind.JsonNode;
import dev.melvstein.spring_portfolio_modulith.common.api.enm.ActionEnum;
import dev.melvstein.spring_portfolio_modulith.common.api.enm.EntityTpeEnum;
import dev.melvstein.spring_portfolio_modulith.common.api.enm.ModuleEnum;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AuditLogEvent(

        ModuleEnum module,
        ActionEnum action,
        EntityTpeEnum entityType,
        UUID entityId,
        UUID actorId,
        String description,
        String ipAddress,
        String userAgent,
        JsonNode before,
        JsonNode after) {
}
