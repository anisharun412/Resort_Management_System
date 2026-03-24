package com.resortmanagement.system.room.dto.response;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomTypeResponse {

    private UUID id;

    private String name;

    private Double baseRate;

    private String bedType;

    private Integer areaSqFt;

    private Integer maxOccupancy;

    private String amenitiesSummary;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
