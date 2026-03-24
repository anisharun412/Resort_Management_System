package com.resortmanagement.system.room.dto.response;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import com.resortmanagement.system.room.enums.HousekeepingPriority;
import com.resortmanagement.system.room.enums.HousekeepingStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HousekeepingTaskResponse {

    private UUID id;
    private UUID roomId;
    private UUID staffId;
    private LocalDateTime scheduledAt;
    private HousekeepingPriority priority;
    private HousekeepingStatus status;
    private String notes;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
