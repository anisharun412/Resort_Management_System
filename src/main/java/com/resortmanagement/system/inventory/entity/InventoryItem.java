package com.resortmanagement.system.inventory.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import com.resortmanagement.system.common.audit.Auditable;

// import lombok.Getter;
// import lombok.Setter;

@Entity
@Table(name = "inventory_items")
public class InventoryItem extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 20, name = "unit")
    private String baseUnit;

    @Column(nullable = false, precision = 15, scale = 3, name = "stock_qty")
    private BigDecimal quantityOnHand;

    @Column(nullable = false, precision = 15, scale = 3, name = "reorder_level")
    private BigDecimal reorderPoint;

    @Column(nullable = false, precision = 15, scale = 2, name = "unit_cost")
    private BigDecimal unitCost;

    @Version
    private Long version;

    // Manual Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public BigDecimal getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(BigDecimal quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public BigDecimal getReorderPoint() {
        return reorderPoint;
    }

    public void setReorderPoint(BigDecimal reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
