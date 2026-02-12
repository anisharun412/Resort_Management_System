-- V6__billing.sql
-- Flyway migration for Billing module
-- MySQL 8+

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS folio (
    folio_id CHAR(36) PRIMARY KEY,
    folio_number VARCHAR(64) NOT NULL UNIQUE,
    reservation_id CHAR(36) NULL,
    booking_guest_id CHAR(36) NULL,
    status ENUM('OPEN','ACTIVE','CLOSED','SETTLED','CANCELLED', 'VOID') NOT NULL DEFAULT 'OPEN',

    total_charges DECIMAL(14,2) NOT NULL DEFAULT 0.00 CHECK (total_charges >= 0),
    total_payments DECIMAL(14,2) NOT NULL DEFAULT 0.00 CHECK (total_payments >= 0),
    balance DECIMAL(14,2) NOT NULL DEFAULT 0.00,

    currency VARCHAR(10) NOT NULL DEFAULT 'INR',

    created_by VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP NULL DEFAULT NULL,

    CONSTRAINT fk_folio_reservation FOREIGN KEY (reservation_id)
        REFERENCES reservation(reservation_id) ON DELETE SET NULL,
    CONSTRAINT fk_folio_booking_guest FOREIGN KEY (booking_guest_id)
        REFERENCES booking_guest(booking_guest_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_folio_reservation ON folio(reservation_id);
CREATE INDEX idx_folio_booking_guest ON folio(booking_guest_id);
CREATE INDEX idx_folio_status ON folio(status);

CREATE TABLE IF NOT EXISTS ledger_transaction (
    transaction_id CHAR(36) PRIMARY KEY,
    folio_id CHAR(36) NOT NULL,
    entry_type ENUM('CHARGE','PAYMENT','REFUND','ADJUSTMENT') NOT NULL,
    reference_id CHAR(36) NULL,         -- optional FK to invoice/payment/order etc
    description TEXT,
    amount DECIMAL(14,2) NOT NULL CHECK (amount >= 0),
    recorded_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    created_by VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_transaction_folio FOREIGN KEY (folio_id)
        REFERENCES folio(folio_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_transaction_folio ON ledger_transaction(folio_id);
CREATE INDEX idx_transaction_type ON ledger_transaction(entry_type);
CREATE INDEX idx_transaction_recorded_at ON ledger_transaction(recorded_at);

CREATE TABLE IF NOT EXISTS account_ledger (
    ledger_id CHAR(36) PRIMARY KEY,
    account_code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    account_type VARCHAR(20) NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00,
    currency VARCHAR(3) DEFAULT 'INR',
    version BIGINT,

    created_by VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_ledger_code ON account_ledger(account_code);
CREATE INDEX idx_ledger_type ON account_ledger(account_type);

CREATE TABLE IF NOT EXISTS invoice (
    invoice_id CHAR(36) PRIMARY KEY,
    invoice_number VARCHAR(64) NOT NULL UNIQUE,
    folio_id CHAR(36) NOT NULL,
    issue_date DATE NOT NULL,
    due_date DATE NULL,
    status ENUM('DRAFT','ISSUED','PAID','CANCELLED') NOT NULL DEFAULT 'DRAFT',

    subtotal DECIMAL(14,2) NOT NULL DEFAULT 0.00 CHECK (subtotal >= 0),
    tax_amount DECIMAL(14,2) NOT NULL DEFAULT 0.00 CHECK (tax_amount >= 0),
    discount_amount DECIMAL(14,2) NOT NULL DEFAULT 0.00 CHECK (discount_amount >= 0),
    total_amount DECIMAL(14,2) NOT NULL DEFAULT 0.00 CHECK (total_amount >= 0),

    created_by VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_invoice_folio FOREIGN KEY (folio_id)
        REFERENCES folio(folio_id) ON DELETE CASCADE,

    CONSTRAINT chk_invoice_dates CHECK (due_date IS NULL OR issue_date <= due_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_invoice_folio ON invoice(folio_id);
CREATE INDEX idx_invoice_status ON invoice(status);
CREATE INDEX idx_invoice_dates ON invoice(issue_date, due_date);

CREATE TABLE IF NOT EXISTS payment (
    payment_id CHAR(36) PRIMARY KEY,
    folio_id CHAR(36) NULL,
    invoice_id CHAR(36) NULL,
    payment_method VARCHAR(64) NOT NULL,    -- e.g., CASH, CARD, NETBANKING, WALLET
    amount DECIMAL(14,2) NOT NULL CHECK (amount >= 0),
    currency VARCHAR(10) NOT NULL DEFAULT 'INR',
    paid_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    transaction_reference VARCHAR(128),

    created_by VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_payment_folio FOREIGN KEY (folio_id)
        REFERENCES folio(folio_id) ON DELETE SET NULL,
    CONSTRAINT fk_payment_invoice FOREIGN KEY (invoice_id)
        REFERENCES invoice(invoice_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_payment_folio ON payment(folio_id);
CREATE INDEX idx_payment_invoice ON payment(invoice_id);
CREATE INDEX idx_payment_paid_at ON payment(paid_at);

CREATE TABLE IF NOT EXISTS refund (
    refund_id CHAR(36) PRIMARY KEY,
    payment_id CHAR(36) NOT NULL,
    invoice_id CHAR(36) NULL,
    folio_id CHAR(36) NULL,
    amount DECIMAL(14,2) NOT NULL CHECK (amount >= 0),
    reason TEXT,
    refunded_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    processed_by CHAR(36),

    created_by VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_refund_payment FOREIGN KEY (payment_id)
        REFERENCES payment(payment_id) ON DELETE CASCADE,
    CONSTRAINT fk_refund_invoice FOREIGN KEY (invoice_id)
        REFERENCES invoice(invoice_id) ON DELETE SET NULL,
    CONSTRAINT fk_refund_folio FOREIGN KEY (folio_id)
        REFERENCES folio(folio_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_refund_payment ON refund(payment_id);
CREATE INDEX idx_refund_invoice ON refund(invoice_id);

-- helpful convenience views/triggers (optional - not created)
-- NOTE: We intentionally did not create triggers to auto-update folio.total_charges/total_payments/balance,
-- because different business rules may apply. It's safer to update folio aggregates in application logic or using
-- carefully-reviewed DB triggers. If you want DB-side auto-aggregation, I can add safe triggers.

SET FOREIGN_KEY_CHECKS = 1;
