package com.resortmanagement.system.booking.dto.response;

import java.time.Instant;
import java.util.UUID;

import com.resortmanagement.system.common.enums.GuestType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingGuestResponse{
    private UUID id;
    private UUID guestId;
    private UUID reservationId;
    private GuestType guestType;
    private Integer age;
    private Boolean isPrimary;
    private String specialNeeds;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
