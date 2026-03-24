package com.resortmanagement.system.booking.dto.response;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record ReservationSummaryResponse(
    UUID id,
    LocalDate checkInDate,
    LocalDate checkOutDate,
    String status,
    String createdBy,
    Instant createdAt,
    String updatedBy,
    Instant updatedAt,
    boolean deleted,
    Instant deletedAt
) {

}
