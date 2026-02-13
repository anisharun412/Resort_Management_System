package com.resortmanagement.system.fnb.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.resortmanagement.system.common.audit.Auditable;
import com.resortmanagement.system.inventory.entity.InventoryItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "menu_item_ingredients")
public class MenuItemIngredient extends Auditable {

    @Id
    @GeneratedValue
    @Column(name="menu_item_ingredient_id")
    private UUID id;

    /**
     * Many ingredients belong to one MenuItem
     * FK: menu_item_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    /**
     * Inventory item used in this recipe line
     * FK: inventory_item_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem inventoryItem;

    /**
     * Quantity required per ONE menu item
     * Stored in inventoryItem.baseUnit
     */
    @Column(name="quantity_required", nullable = false, precision = 10, scale = 3)
    private BigDecimal quantityRequired;

    /**
     * Human-readable unit (e.g., "grams", "ml", "pieces")
     * Conversions handled at write-time
     */
    @Column(nullable = false)
    private String unit;
}
