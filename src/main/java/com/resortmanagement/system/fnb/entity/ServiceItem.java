package com.resortmanagement.system.fnb.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.resortmanagement.system.common.audit.AuditableSoftDeletable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service_items")
@Getter
@Setter
public class ServiceItem extends AuditableSoftDeletable {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name="service_item_id")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    /**
     * Duration in minutes (e.g., spa = 60 mins)
     */
    @Column(name = "duration_mins")
    private Integer durationMins;

    @Column(name="base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    /**
     * Category like SPA, LAUNDRY, SHUTTLE
     * Kept as String to avoid premature enum coupling
     */
    @Column(nullable = false)
    private String category;

    //is_active BOOLEAN NOT NULL DEFAULT TRUE,
}