ALTER TABLE audit.audit_logs
    ADD COLUMN before JSONB NOT NULL DEFAULT '{}'::jsonb,
    ADD COLUMN after JSONB NOT NULL DEFAULT '{}'::jsonb;

CREATE INDEX idx_audit_logs_before
    ON audit.audit_logs
    USING GIN (before);

CREATE INDEX idx_audit_logs_after
    ON audit.audit_logs
    USING GIN (after);