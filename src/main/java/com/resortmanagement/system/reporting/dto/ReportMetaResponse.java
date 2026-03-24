package com.resortmanagement.system.reporting.dto;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class ReportMetaResponse {
    private UUID id;
    private String name;
    private String scheduleString;
    private Instant lastRunAt;
    private UUID ownerId;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
