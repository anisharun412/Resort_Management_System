package com.resortmanagement.system.inventory.mapper;

import org.springframework.stereotype.Component;

import com.resortmanagement.system.inventory.dto.response.InventoryTransactionResponse;
import com.resortmanagement.system.inventory.entity.InventoryTransaction;

@Component
public class InventoryTransactionMapper {

    public InventoryTransactionResponse toResponse(InventoryTransaction entity) {
        if (entity == null) {
            return null;
        }
        InventoryTransactionResponse response = new InventoryTransactionResponse();
        response.setId(entity.getId());
        response.setInventoryItemId(entity.getItem().getId());
        response.setInventoryItemName(entity.getItem().getName());
        response.setQtyChange(entity.getQtyChange());
        response.setSourceType(entity.getSourceType());
        response.setSourceId(entity.getSourceId());
        response.setCreatedBy(entity.getCreatedBy());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedBy(entity.getUpdatedBy());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
