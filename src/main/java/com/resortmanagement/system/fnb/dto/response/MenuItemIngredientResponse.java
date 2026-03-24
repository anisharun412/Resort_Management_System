package com.resortmanagement.system.fnb.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemIngredientResponse {
    private UUID id;
    private UUID menuItemId;
    private String menuItemName;
    private UUID inventoryItemId;
    private String inventoryItemName;
    private BigDecimal quantityRequired;
    private String unit;
    // Audit fields (Auditable only)
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
}