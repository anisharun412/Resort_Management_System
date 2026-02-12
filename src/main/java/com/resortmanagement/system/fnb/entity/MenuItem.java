package com.resortmanagement.system.fnb.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.resortmanagement.system.common.audit.AuditableSoftDeletable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "menu_items")
public class MenuItem extends AuditableSoftDeletable {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "menu_item_id")
    private UUID id;

    /**
     * Many MenuItems belong to one Menu
     * FK: menu_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 100)
    private String sku;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "prep_time_mins")
    private Integer prepTimeMins;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable = true;

    /**
     * MenuItem → MenuItemIngredient
     * Inventory linkage happens via this mapping
     */
    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MenuItemIngredient> ingredients = new HashSet<>();

    
}
