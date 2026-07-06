package dev.melvstein.spring_portfolio_modulith.audit.api.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import dev.melvstein.spring_portfolio_modulith.common.api.enm.ActionEnum;
import dev.melvstein.spring_portfolio_modulith.common.api.enm.EntityTpeEnum;
import dev.melvstein.spring_portfolio_modulith.common.api.enm.ModuleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "audit", name = "audit_logs")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ModuleEnum module;

    @Enumerated(EnumType.STRING)
    private ActionEnum action;

    @Enumerated(EnumType.STRING)
    private EntityTpeEnum entityType;

    private UUID entityId;
    private UUID actorId;
    private String description;
    private String ipAddress;
    private String userAgent;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false)
    @Builder.Default
    private JsonNode before = JsonNodeFactory.instance.objectNode();

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false)
    @Builder.Default
    private JsonNode after = JsonNodeFactory.instance.objectNode();

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
