package com.resortmanagement.system.fnb.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.resortmanagement.system.common.audit.AuditableSoftDeletable;

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
@Table(name = "order_items")
public class OrderItem extends AuditableSoftDeletable {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name="order_item_id")
    private UUID id;

    /**
     * Many OrderItems belong to one Order
     * FK: order_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * Menu item being ordered
     * FK: menu_item_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(nullable = false)
    private Integer quantity;

    /**
     * Snapshot price from MenuItem at order time
     */
    @Column(name="unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    /**
     * qty * unitPrice
     */
    @Column(name="total_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPrice;
 
}
