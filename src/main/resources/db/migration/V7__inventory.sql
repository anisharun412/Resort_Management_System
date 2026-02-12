-- V7__inventory.sql
-- Flyway migration for Inventory module
-- MySQL 8+

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS suppliers (
    supplier_id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contact_name VARCHAR(200),
    email VARCHAR(200),
    phone VARCHAR(50),
    address TEXT,
    country VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_by CHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by CHAR(36),
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_suppliers_name ON suppliers(name);
CREATE INDEX idx_suppliers_active ON suppliers(is_active);

CREATE TABLE IF NOT EXISTS inventory_item (
    inventory_item_id CHAR(36) PRIMARY KEY,
    sku VARCHAR(100) UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    unit VARCHAR(50) DEFAULT 'unit',         -- e.g., kg, litre, piece
    unit_cost DECIMAL(14,4) DEFAULT 0.0000 CHECK (unit_cost >= 0),
    stock_qty DECIMAL(18,4) DEFAULT 0.0000 CHECK (stock_qty >= 0), -- support fractional inventory
    reorder_level DECIMAL(18,4) DEFAULT 0.0000,
    reorder_qty DECIMAL(18,4) DEFAULT 0.0000,
    location VARCHAR(200),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_by CHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by CHAR(36),
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP NULL DEFAULT NULL,

    CONSTRAINT chk_inventory_unit_cost_check CHECK (unit_cost >= 0),
    CONSTRAINT chk_inventory_stock_nonnegative CHECK (stock_qty >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_inventory_sku ON inventory_item(sku);
CREATE INDEX idx_inventory_name ON inventory_item(name);
CREATE INDEX idx_inventory_active ON inventory_item(is_active);

CREATE TABLE IF NOT EXISTS purchase_order (
    purchase_order_id CHAR(36) PRIMARY KEY,
    po_number VARCHAR(64) NOT NULL UNIQUE,
    supplier_id CHAR(36) NULL,
    status ENUM('DRAFT','PLACED','PARTIAL_RECEIVED','RECEIVED','CANCELLED') NOT NULL DEFAULT 'DRAFT',
    order_date DATE NOT NULL,
    expected_date DATE NULL,
    subtotal DECIMAL(14,2) NOT NULL DEFAULT 0.00 CHECK (subtotal >= 0),
    tax_amount DECIMAL(14,2) NOT NULL DEFAULT 0.00 CHECK (tax_amount >= 0),
    total_amount DECIMAL(14,2) NOT NULL DEFAULT 0.00 CHECK (total_amount >= 0),
    currency VARCHAR(10) NOT NULL DEFAULT 'INR',
    notes TEXT,

    created_by CHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by CHAR(36),
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_po_supplier FOREIGN KEY (supplier_id)
        REFERENCES suppliers(supplier_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_po_supplier ON purchase_order(supplier_id);
CREATE INDEX idx_po_status ON purchase_order(status);
CREATE INDEX idx_po_order_date ON purchase_order(order_date);

CREATE TABLE IF NOT EXISTS purchase_order_line (
    po_line_id CHAR(36) PRIMARY KEY,
    purchase_order_id CHAR(36) NOT NULL,
    inventory_item_id CHAR(36) NULL,
    description TEXT,
    quantity DECIMAL(18,4) NOT NULL DEFAULT 0.0000 CHECK (quantity > 0),
    unit_price DECIMAL(14,4) NOT NULL DEFAULT 0.0000 CHECK (unit_price >= 0),
    received_qty DECIMAL(18,4) NOT NULL DEFAULT 0.0000 CHECK (received_qty >= 0),
    -- computed line total (stored)
    line_total DECIMAL(18,4) GENERATED ALWAYS AS (quantity * unit_price) STORED,

    created_by CHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by CHAR(36),
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_po_line_po FOREIGN KEY (purchase_order_id)
        REFERENCES purchase_order(purchase_order_id) ON DELETE CASCADE,
    CONSTRAINT fk_po_line_item FOREIGN KEY (inventory_item_id)
        REFERENCES inventory_item(inventory_item_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_po_line_po ON purchase_order_line(purchase_order_id);
CREATE INDEX idx_po_line_item ON purchase_order_line(inventory_item_id);

CREATE TABLE IF NOT EXISTS inventory_transaction (
    transaction_id CHAR(36) PRIMARY KEY,
    inventory_item_id CHAR(36) NOT NULL,
    transaction_type ENUM('IN','OUT','ADJUSTMENT','TRANSFER','PO_RECEIPT') NOT NULL,
    quantity DECIMAL(18,4) NOT NULL CHECK (quantity <> 0),
    unit_cost DECIMAL(14,4) DEFAULT 0.0000 CHECK (unit_cost >= 0),
    reference_id CHAR(36) NULL,   -- optional pointer to PO, PO_LINE, REFUND, etc.
    reference_type VARCHAR(100) NULL, -- e.g., 'PO', 'PO_LINE', 'ADJUST', 'SALE'
    warehouse_location_from VARCHAR(200) NULL,
    warehouse_location_to VARCHAR(200) NULL,
    transaction_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    created_by CHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_tx_item FOREIGN KEY (inventory_item_id)
        REFERENCES inventory_item(inventory_item_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_tx_item ON inventory_transaction(inventory_item_id);
CREATE INDEX idx_tx_type ON inventory_transaction(transaction_type);
CREATE INDEX idx_tx_date ON inventory_transaction(transaction_date);

-- Helpful safety / documentation notes (no triggers applied)
-- NOTE:
-- * This migration intentionally does not create triggers that automatically update inventory_item.stock_qty when
--   inventory_transaction rows are inserted. Inventory adjustments and PO receipts are sensitive operations and
--   typically require business-rule validation and concurrent-safe logic (application-level or carefully tested triggers).
-- * If you want DB-side stock updates, I can add a safe trigger set using SELECT ... FOR UPDATE and transactional logic.
-- * If you need composite unique constraints or additional fields (batch numbers, expiry dates, lot tracking), tell me
--   and I will extend the schema.

SET FOREIGN_KEY_CHECKS = 1;
