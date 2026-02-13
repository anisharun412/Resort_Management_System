package com.resortmanagement.system.inventory.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.resortmanagement.system.common.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// import lombok.Getter;
// import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventory_transaction")
public class InventoryTransaction extends Auditable {

    @Id
    @Column(name = "transaction_id")
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem item;

    @Column(name = "quantity", nullable = false)
    private BigDecimal qtyChange;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private InventorySourceType sourceType;

    @Column(name = "reference_id")
    private UUID sourceId;

    @Column(name = "reference_type")
    private String referenceType;

    @Column(name = "unit_cost")
    private BigDecimal unitCost;

    @Column(name = "transaction_date")
    private java.time.LocalDateTime transactionDate;

}
