package com.resortmanagement.system.inventory.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryItemResponse {
    private UUID id;
    private String sku;
    private String name;
    private String baseUnit;
    private BigDecimal quantityOnHand;
    private BigDecimal reorderPoint;
    private BigDecimal unitCost;
    
    // Audit fields (Auditable)
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
}
