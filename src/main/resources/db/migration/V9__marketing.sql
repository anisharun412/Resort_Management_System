-- V9__marketing.sql
-- Create Marketing tables

CREATE TABLE promotions (
    id CHAR(36) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    code VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    discount_type VARCHAR(255) NOT NULL,
    terms VARCHAR(255),
    usage_limit INT,
    valid_from DATE NOT NULL,
    valid_to DATE NOT NULL,
    value DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_promotions_code UNIQUE (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE packages (
    id CHAR(36) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    description VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    usage_limit INT,
    valid_from DATE NOT NULL,
    valid_to DATE NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE package_items (
    id CHAR(36) NOT NULL,
    component_id CHAR(36) NOT NULL,
    component_type VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    qty INT NOT NULL,
    package_id CHAR(36) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_package_items_package FOREIGN KEY (package_id) REFERENCES packages (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE promotion_packages (
    promotion_id CHAR(36) NOT NULL,
    package_id CHAR(36) NOT NULL,
    PRIMARY KEY (promotion_id, package_id),
    CONSTRAINT fk_prom_pack_prom FOREIGN KEY (promotion_id) REFERENCES promotions (id) ON DELETE CASCADE,
    CONSTRAINT fk_prom_pack_pack FOREIGN KEY (package_id) REFERENCES packages (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE reservation
ADD CONSTRAINT fk_reservation_package
FOREIGN KEY (package_id)
REFERENCES packages(id);

CREATE TABLE loyalty_members (
    id CHAR(36) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    enrolled_at DATETIME(6) NOT NULL,
    guest_id CHAR(36) NOT NULL,
    points_balance DECIMAL(10,2) NOT NULL,
    status VARCHAR(255) NOT NULL,
    tier VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_loyalty_members_guest UNIQUE (guest_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
