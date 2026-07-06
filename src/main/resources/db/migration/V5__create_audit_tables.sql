CREATE TABLE audit.audit_logs (
    id UUID PRIMARY KEY,
    module VARCHAR(50) NOT NULL,
    action VARCHAR(100) NOT NULL,
    entity_type VARCHAR(100) NOT NULL,
    entity_id UUID,
    actor_id UUID,
    description TEXT,
    ip_address VARCHAR(45),
    user_agent TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_audit_logs_actor_id
    ON audit.audit_logs(actor_id);

CREATE INDEX idx_audit_logs_entity
    ON audit.audit_logs(entity_type, entity_id);

CREATE INDEX idx_audit_logs_module
    ON audit.audit_logs(module);

CREATE INDEX idx_audit_logs_created_at
    ON audit.audit_logs(created_at);

CREATE INDEX idx_audit_logs_updated_at
    ON audit.audit_logs(updated_at);