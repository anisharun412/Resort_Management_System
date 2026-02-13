package com.resortmanagement.system.fnb.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.resortmanagement.system.common.audit.AuditableSoftDeletable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "menus")
public class Menu extends AuditableSoftDeletable {

    @Id
    @GeneratedValue
    @Column(name="menu_id")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    /**
     * Menu → MenuItem (bidirectional)
     * FK lives in MenuItem (menu_id)
     */
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MenuItem> menuItems = new HashSet<>();

  
}
