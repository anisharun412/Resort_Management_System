package com.resortmanagement.system.fnb.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.resortmanagement.system.common.enums.ActivityEventStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityEventResponse {
    private UUID id;
    private String title;
    private String description;
    private Instant startTime;
    private Instant endTime;
    private int capacity;
    private UUID instructorId;
    private BigDecimal price;
    private ActivityEventStatus status;
    // Audit fields (AuditableSoftDeletable)
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
