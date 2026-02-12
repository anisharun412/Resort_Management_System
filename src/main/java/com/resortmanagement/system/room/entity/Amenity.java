package com.resortmanagement.system.room.entity;

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

@Entity
@Getter
@Setter
@Table(name = "amenities")
public class Amenity extends AuditableSoftDeletable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;       
    private String description;
    private String category;    
}
