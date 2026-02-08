/*
Purpose:
 - Central Guest entity used across domains (booking, fnb, billing).
Fields:
 - id: UUID
 - firstName, lastName, email, phone, address, dob
 - loyaltyId: String (nullable)
 - status: enum (ACTIVE, INACTIVE)
 - extends Auditable (track who created/modified guest)
 - soft-delete optional for GDPR (see policy)
Guidelines:
 - Validation annotations (email @Email)
 - Keep sensitive data encryption in mind if needed (passport numbers etc).
 - Provide mapping to Guest DTOs for external API.
File: common/Guest/Guest.java
*/

package com.resortmanagement.system.common.guest;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.resortmanagement.system.common.audit.AuditableSoftDeletable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "guest")
@Getter
@Setter
public class Guest extends AuditableSoftDeletable {

    @Id
    @UuidGenerator
    @Column(name = "guest_id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "loyalty_id", length = 50)
    private String loyaltyId;
}