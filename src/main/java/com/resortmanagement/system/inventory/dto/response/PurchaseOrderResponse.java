package com.resortmanagement.system.inventory.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.resortmanagement.system.inventory.entity.PurchaseOrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderResponse {
    private UUID id;
    private String poNumber;
    private UUID supplierId;
    private String supplierName;
    private PurchaseOrderStatus status;
    private Instant expectedDelivery;
    private BigDecimal totalAmount;
    private List<PurchaseOrderLineResponse> lines;

    // Audit fields (Auditable)
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
}
