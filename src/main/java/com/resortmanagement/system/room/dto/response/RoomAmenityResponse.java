package com.resortmanagement.system.room.dto.response;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomAmenityResponse {

    private UUID id;
    private UUID roomId;
    private UUID amenityId;
    private Boolean complimentary;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
}
