-- FEEDBACK REVIEWS
CREATE TABLE feedback_reviews (
    id CHAR(36) PRIMARY KEY,
    guest_id CHAR(36) NOT NULL,
    rating INT,
    comments TEXT,
    response_by CHAR(36),
    responded_at TIMESTAMP,
    reservation_id CHAR(36),

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted BOOLEAN DEFAULT FALSE,
    deleted_at TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- COMMUNICATIONS
CREATE TABLE communications (
    id CHAR(36) PRIMARY KEY,
    guest_id CHAR(36),
    type VARCHAR(30),
    recipient VARCHAR(255),
    subject VARCHAR(255),
    body_snippet TEXT,
    status VARCHAR(30),
    sent_at TIMESTAMP,
    channel VARCHAR(50),
    reservation_id CHAR(36),

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted BOOLEAN DEFAULT FALSE,
    deleted_at TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- HELP TICKETS
CREATE TABLE help_tickets (
    id CHAR(36) PRIMARY KEY,
    guest_id CHAR(36) NOT NULL,
    ticket_number VARCHAR(100),
    category VARCHAR(100),
    description TEXT,
    priority VARCHAR(30),
    status VARCHAR(30),
    assigned_to CHAR(36),
    reservation_id CHAR(36),
    resolved_at TIMESTAMP,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted BOOLEAN DEFAULT FALSE,
    deleted_at TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
