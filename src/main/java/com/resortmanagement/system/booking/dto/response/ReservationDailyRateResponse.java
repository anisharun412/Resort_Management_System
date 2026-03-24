package com.resortmanagement.system.booking.dto.response;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDailyRateResponse{
    private UUID Id;
    private LocalDate date;
    private Boolean isPackageRate;
    private Double amount;
    private UUID ratePlanId;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
}
