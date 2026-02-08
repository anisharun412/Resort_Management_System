/**
 * TODO: AccountLedger.java
 * Purpose:
    - Entity for accounting ledger/accounts used by financial flows (room revenue, F&B, cash).
 * Annotations & fields:
    - @Entity, @Table(name = "account_ledger")
    - id: UUID PK
    - accountCode String (unique)
    - name String
    - accountType enum (ASSET/LIABILITY/REVENUE/EXPENSE)
    - balance BigDecimal (nullable, use BigDecimal)
    - currency String (ISO)
    - @Version Long version (optimistic locking)
    - extends Auditable (since ledger updates are critical for audit)
    - soft-delete not required but allowed for archival

 * Behavior:
    - Keep minimal logic in entity (no methods that mutate business state).
    - Use Repository for CRUD and Service for transactional updates.

 * Usage:
    - Ledger adjustments performed through AccountLedgerService (transactional).

 * File: billing/entity/AccountLedger.java
 */
package com.resortmanagement.system.billing.entity;

public class AccountLedger {
   // TODO: fields, constructors, getters, setters
}