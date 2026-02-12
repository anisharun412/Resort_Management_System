CREATE TABLE audit_log (
    audit_id CHAR(36) NOT NULL,

    target_entity VARCHAR(100) NOT NULL,
    target_id CHAR(36) NOT NULL,

    action VARCHAR(20) NOT NULL,
    performed_by VARCHAR(100) NOT NULL,

    `timestamp` DATETIME(6) NOT NULL,

    changes_json TEXT NULL,
    reason VARCHAR(500) NULL,

    PRIMARY KEY (audit_id),

    INDEX idx_audit_target_entity (target_entity),
    INDEX idx_audit_target_id (target_id),
    INDEX idx_audit_timestamp (`timestamp`)
);

CREATE TABLE report_meta (
    report_id CHAR(36) NOT NULL,

    name VARCHAR(100) NOT NULL,
    schedule_string VARCHAR(100) NULL,
    last_run_at DATETIME(6) NULL,
    owner_id CHAR(36) NULL,

   -- Auditing fields
    created_by VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NOT NULL,
    
    PRIMARY KEY (report_id),

    INDEX idx_report_name (name),
    INDEX idx_report_owner (owner_id)
);
