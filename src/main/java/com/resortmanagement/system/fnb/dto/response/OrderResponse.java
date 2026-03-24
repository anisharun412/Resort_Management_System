package com.resortmanagement.system.fnb.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private UUID id;
    private UUID guestId;
    private UUID reservationId;
    private UUID tableId;
    private BigDecimal totalAmount;
    private String status;
    private Instant placedAt;
    private UUID assignedFolioId;
    private List<OrderItemResponse> items;
    // Audit fields (AuditableSoftDeletable)
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
