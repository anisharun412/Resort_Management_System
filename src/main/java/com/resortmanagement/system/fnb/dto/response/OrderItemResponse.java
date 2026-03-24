package com.resortmanagement.system.fnb.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    private UUID id;
    private UUID menuItemId;
    private String menuItemName;
    private int qty;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    // Audit fields (AuditableSoftDeletable)
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
