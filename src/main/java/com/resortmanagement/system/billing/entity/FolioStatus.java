package com.resortmanagement.system.billing.entity;

/**
 * FolioStatus enum
 * Purpose: Represents the status of a folio (billing bucket)
 * Values:
 *  - OPEN: Folio is open and charges can be added
 *  - CLOSED: Folio is closed and ready for invoicing
 *  - VOID: Folio is voided (incorrect/cancelled, but record retained)
 */
public enum FolioStatus {
    OPEN,
    CLOSED,
    ACTIVE,
    SETTLED,
    CANCELLED,
    VOID
}
