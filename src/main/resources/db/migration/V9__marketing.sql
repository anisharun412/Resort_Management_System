-- V9__marketing.sql
-- Create Marketing tables

CREATE TABLE promotions (
    id BINARY(16) NOT NULL,
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
) ENGINE=InnoDB;

CREATE TABLE packages (
    id BINARY(16) NOT NULL,
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
) ENGINE=InnoDB;

CREATE TABLE package_items (
    id BINARY(16) NOT NULL,
    component_id BINARY(16) NOT NULL,
    component_type VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    qty INT NOT NULL,
    package_id BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_package_items_package FOREIGN KEY (package_id) REFERENCES packages (id)
) ENGINE=InnoDB;

CREATE TABLE loyalty_members (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    enrolled_at DATETIME(6) NOT NULL,
    guest_id BINARY(16) NOT NULL,
    points_balance DECIMAL(10,2) NOT NULL,
    status VARCHAR(255) NOT NULL,
    tier VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_loyalty_members_guest UNIQUE (guest_id)
) ENGINE=InnoDB;
