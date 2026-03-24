package com.resortmanagement.system.booking.dto.response;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import com.resortmanagement.system.common.enums.ServiceBookingStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationServiceBookingResponse{
    private UUID id;
    private UUID serviceItemId;
    private LocalDateTime scheduledAt;
    private Integer quantity;
    private Double price;
    private ServiceBookingStatus status;
    private Boolean IncludedInPackage;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
