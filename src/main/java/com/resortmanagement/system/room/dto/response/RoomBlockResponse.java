package com.resortmanagement.system.room.dto.response;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import com.resortmanagement.system.room.enums.BlockStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomBlockResponse {

    private UUID id;
    private UUID roomId;
    private UUID maintenanceRequestId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String reason;
    private BlockStatus status;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
