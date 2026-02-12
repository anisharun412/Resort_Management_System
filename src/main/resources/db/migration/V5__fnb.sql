-- V5__fnb.sql
-- Flyway migration for FNB (Food & Beverage) module
-- MySQL 8+

SET FOREIGN_KEY_CHECKS = 0;

-- ============================================================
-- COMMON AUDIT (pattern used across migrations)
-- ============================================================
-- We include audit/soft-delete fields in each table (application-level UUIDs)
-- created_by / modified_by reference application UUIDs (CHAR(36)) — no FK enforced.

-- ============================================================
-- menus
-- ============================================================
CREATE TABLE IF NOT EXISTS menus (
    menu_id CHAR(36) PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,

    -- Soft delete support
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP NULL,

    -- Auditing fields
    created_by VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ============================================================
-- menu_items
-- ============================================================
CREATE TABLE IF NOT EXISTS menu_items (
    menu_item_id CHAR(36) PRIMARY KEY,
    menu_id CHAR(36) NOT NULL,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    sku VARCHAR(100),
    price DECIMAL(10,2) NOT NULL CHECK (price >= 0),
    prep_time_mins INT DEFAULT 0,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,

    -- Soft delete support
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP NULL,

    -- Auditing fields
    created_by VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_menu_item_menu FOREIGN KEY (menu_id)
        REFERENCES menus(menu_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ============================================================
-- menu_item_ingredients
-- ============================================================
CREATE TABLE IF NOT EXISTS menu_item_ingredient (
    menu_item_ingredient_id CHAR(36) PRIMARY KEY,
    menu_item_id CHAR(36) NOT NULL,
    inventory_item_id CHAR(36) NOT NULL,
    quantity_required DECIMAL(10,3) NOT NULL DEFAULT 0,        -- amount per menu item
    unit VARCHAR(50),

    -- Auditing fields
    created_by VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_ingredient_menu_item FOREIGN KEY (menu_item_id)
        REFERENCES menu_items(menu_item_id) ON DELETE CASCADE,
    CONSTRAINT fk_inventory_item FOREIGN KEY (inventory_item_id)
        REFERENCES inventory_items(inventory_item_id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- service_items (Activity-like items: spa, shuttle, laundry, etc.)
-- ============================================================
CREATE TABLE IF NOT EXISTS service_items (
    service_item_id CHAR(36) PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    duration_mins INT DEFAULT 0,
    base_price DECIMAL(10,2) NOT NULL CHECK (base_price >= 0),
    category VARCHAR(100),    -- e.g., SPA, LAUNDRY, SHUTTLE

    -- Soft delete support
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP NULL,

    -- Auditing fields
    created_by VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- orders (FNB orders; optionally tied to reservation / booking guest)
CREATE TABLE IF NOT EXISTS orders (
    order_id CHAR(36) PRIMARY KEY,
    reservation_id CHAR(36) NULL,
    booking_guest_id CHAR(36) NULL,
    table_id CHAR(36) NULL,  -- for restaurant orders; null for POS or service orders
    status ENUM('CREATED','PLACED','IN_PROGRESS','SERVED','PAID','CANCELLED') NOT NULL DEFAULT 'CREATED',

    -- subtotal DECIMAL(12,2) NOT NULL DEFAULT 0.00 CHECK (subtotal >= 0),
    -- tax_amount DECIMAL(12,2) NOT NULL DEFAULT 0.00 CHECK (tax_amount >= 0),
    -- discount_amount DECIMAL(12,2) NOT NULL DEFAULT 0.00 CHECK (discount_amount >= 0),
    total_amount DECIMAL(12,2) NOT NULL DEFAULT 0.00 CHECK (total_amount >= 0),
    currency VARCHAR(10) NOT NULL DEFAULT 'INR',

    placed_at TIMESTAMP NULL DEFAULT NULL,
    -- served_at TIMESTAMP NULL DEFAULT NULL,
    -- paid_at TIMESTAMP NULL DEFAULT NULL,

    assigned_folio_id CHAR(36) NULL,  -- linked folio for billing (nullable until assigned)Sign in to enable AI completions, or disable inline completions in Settings (DBCode > AI).

    -- Soft delete support
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP NULL,

    -- Auditing fields
    created_by VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_order_reservation FOREIGN KEY (reservation_id)
        REFERENCES reservation(reservation_id) ON DELETE SET NULL,
    CONSTRAINT fk_order_booking_guest FOREIGN KEY (booking_guest_id)
        REFERENCES booking_guest(booking_guest_id) ON DELETE SET ,
    CONSTRAINT fk_order_folio FOREIGN KEY (assigned_folio_id)
        REFERENCES folio(folio_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_orders_status ON orders(status);
CREATE INDEX idx_orders_reservation ON orders(reservation_id);
CREATE INDEX idx_orders_booking_guest ON orders(booking_guest_id);

CREATE TABLE IF NOT EXISTS order_items (
    order_item_id CHAR(36) PRIMARY KEY,
    order_id CHAR(36) NOT NULL,
    menu_item_id CHAR(36) NULL,
    quantity INT NOT NULL DEFAULT 1 CHECK (quantity > 0),
    unit_price DECIMAL(12,2) NOT NULL CHECK (unit_price >= 0),
    total_price DECIMAL(12,2) GENERATED ALWAYS AS (quantity * unit_price) STORED,

    -- Soft delete support
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP NULL,

    -- Auditing fields
    created_by VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_order_item_order FOREIGN KEY (order_id)
        REFERENCES orders(order_id) ON DELETE CASCADE,
    CONSTRAINT fk_order_item_menu_item FOREIGN KEY (menu_item_id)
        REFERENCES menu_items(menu_item_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS activity_events (
    activity_event_id CHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    capacity INT NOT NULL,
    instructor_id CHAR(36) NOT NULL, -- Mapped to instructorId in Entity (likely a mistake in Entity, but keeping column name for now)
    price DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) NOT NULL,

    -- Soft delete support
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP NULL,

    -- Auditing fields
    created_by VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NOT NULL

    CONSTRAINT fk_activity_event_instructor FOREIGN KEY (instructor_id)
        REFERENCES employees(employee_id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
