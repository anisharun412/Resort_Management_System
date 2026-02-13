package com.resortmanagement.system.inventory.entity;

import java.util.UUID;

import com.resortmanagement.system.common.audit.AuditableSoftDeletable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class Supplier extends AuditableSoftDeletable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="supplier_id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name="contact_person")
    private String contactPerson;

    @Column(name="phone", length = 50)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String address;

    
}
