package com.resortmanagement.system.booking.dto.response;

import java.time.Instant;
import java.util.UUID;

import com.resortmanagement.system.common.enums.AddOnStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationAddOnResponse{
    private UUID id;
    private String addOnCode;
    private String addOnName;
    private Integer quantity;
    private Double totalPrice;
    private Double unitPrice;
    private AddOnStatus status;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
