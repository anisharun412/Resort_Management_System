package com.resortmanagement.system.inventory.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.resortmanagement.system.inventory.entity.InventorySourceType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryTransactionResponse {
    private UUID id;
    private UUID inventoryItemId;
    private String inventoryItemName;
    private BigDecimal qtyChange;
    private InventorySourceType sourceType;
    private UUID sourceId;

    // Audit fields (Auditable)
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
}
