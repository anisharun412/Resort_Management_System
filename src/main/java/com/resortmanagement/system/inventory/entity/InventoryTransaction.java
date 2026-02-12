package com.resortmanagement.system.inventory.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// import lombok.Getter;
// import lombok.Setter;

@Entity
@Table(name = "inventory_transaction")
public class InventoryTransaction {

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

    // Manual Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
    }

    public BigDecimal getQtyChange() {
        return qtyChange;
    }

    public void setQtyChange(BigDecimal qtyChange) {
        this.qtyChange = qtyChange;
    }

    public InventorySourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(InventorySourceType sourceType) {
        this.sourceType = sourceType;
    }

    public UUID getSourceId() {
        return sourceId;
    }

    public void setSourceId(UUID sourceId) {
        this.sourceId = sourceId;
    }
}
