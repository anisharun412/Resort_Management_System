package com.resortmanagement.system.inventory.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "purchase_order_lines")
public class PurchaseOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="po_line_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem inventoryItem;

    @Column(name="quantity", nullable = false, precision = 15, scale = 3)
    private BigDecimal qty;

    @Column(name="unit_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(name="total_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalPrice;

    //    received_qty DECIMAL(18,4) NOT NULL DEFAULT 0.0000 CHECK (received_qty >= 0),
    // -- computed line total (stored)
}
