/**
 * Invoice.java
 * Purpose:
 *  - Persisted financial document generated from folio charges.
 * Fields:
 *  - id: UUID
 *  - folioId: UUID (reference to Folio)
 *  - reservationId: UUID (optional)
 *  - guestId: UUID
 *  - issueDate: Instant
 *  - dueDate: Instant
 *  - totalAmount: BigDecimal
 *  - taxAmount: BigDecimal
 *  - status enum (DRAFT, ISSUED, PAID, PARTIALLY_PAID, REFUNDED, CANCELLED)
 *  - currency String
 *  - extends Auditable (must track who issued)
 *  - store request/response snapshot JSON if needed (for dispute)
 * Notes:
 *  - Immutable once ISSUED except for credit/refund operations stored separately.
 *  - Use InvoiceService to compute totals; never compute in controllers.
 *
 * File: billing/entity/Invoice.java
 */
package com.resortmanagement.system.billing.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.resortmanagement.system.booking.entity.Reservation;
import com.resortmanagement.system.common.audit.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invoice")
@Getter
@Setter
public class Invoice extends Auditable {

    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "invoice_id", columnDefinition = "CHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "folio_id", columnDefinition = "CHAR(36)")
    private UUID folioId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "reservation_id", columnDefinition = "CHAR(36)")
    private UUID reservationId;

    @Column(name = "invoice_number", nullable = false, unique = true, length = 64)
    private String invoiceNumber;

    @NotNull
    @Column(name = "issue_date", nullable = false)
    private Instant issueDate;

    @Column(name = "due_date")
    private Instant dueDate;

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "tax_amount", precision = 10, scale = 2)
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private InvoiceStatus status = InvoiceStatus.DRAFT;

    @Column(name = "currency", length = 3)
    private String currency = "INR";

    @Version
    @Column(name = "version")
    private Long version;

    // JPA Relationships - Financial record chain: Folio -> Invoice -> Payment ->
    // Refund

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folio_id", insertable = false, updatable = false)
    private Folio folio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", insertable = false, updatable = false)
    private Reservation reservation; // Reference to Reservation entity (avoiding direct import to prevent circular
    // dependency)

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Invoice))
            return false;
        Invoice invoice = (Invoice) o;
        return id != null && id.equals(invoice.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}