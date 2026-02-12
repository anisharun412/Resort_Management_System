-- V1__common.sql
-- Common foundational tables (shared across domains)
-- Owned by lead / architect

CREATE TABLE guest (
    guest_id CHAR(36) PRIMARY KEY,

    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    email      VARCHAR(150) NOT NULL,
    phone      VARCHAR(20),
    address    VARCHAR(255),
    dob        DATE,
    loyalty_id VARCHAR(50),

    -- Soft delete support
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP NULL,

    -- Auditing fields
    created_by VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT uk_guest_email UNIQUE (email)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
