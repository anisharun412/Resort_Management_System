package com.resortmanagement.system.fnb.dto.response;

import java.time.Instant;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuResponse {
    private UUID id;
    private String name;
    private String description;
    // Audit fields (AuditableSoftDeletable)
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
