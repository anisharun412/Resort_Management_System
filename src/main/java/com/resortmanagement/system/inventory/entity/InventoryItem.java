package com.resortmanagement.system.inventory.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.resortmanagement.system.common.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventory_items")
public class InventoryItem extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="inventory_item_id", updatable = false, nullable = false)  
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(name="base_unit", nullable = false)
    private String baseUnit;

    @Column(name="quantity_on_hand", nullable = false, precision = 15, scale = 3)
    private BigDecimal quantityOnHand;

    @Column(name="reorder_point", nullable = false, precision = 15, scale = 3)
    private BigDecimal reorderPoint;

    @Column(name="unit_cost", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitCost;

    @Column(name="reorder_qty")
    private BigDecimal reorderQty;

    @Version
    private Long version;
    
}
