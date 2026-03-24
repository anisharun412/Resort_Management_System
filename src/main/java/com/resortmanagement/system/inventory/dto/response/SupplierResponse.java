package com.resortmanagement.system.inventory.dto.response;

import java.time.Instant;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierResponse {
    private UUID id;
    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;

    // Audit fields (AuditableSoftDeletable)
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
