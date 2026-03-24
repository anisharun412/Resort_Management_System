package com.resortmanagement.system.inventory.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderLineResponse {
    private UUID id;
    private UUID inventoryItemId;
    private String inventoryItemName;
    private BigDecimal qty;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
