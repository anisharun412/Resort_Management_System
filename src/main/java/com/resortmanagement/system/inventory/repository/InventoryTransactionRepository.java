package com.resortmanagement.system.inventory.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.inventory.entity.InventorySourceType;
import com.resortmanagement.system.inventory.entity.InventoryTransaction;

@Repository
public interface InventoryTransactionRepository
        extends JpaRepository<InventoryTransaction, UUID> {

    /**
     * Find all transactions for a specific inventory item
     */
    List<InventoryTransaction> findByItemId(UUID itemId);

    /**
     * Find transactions by source (ORDER, PURCHASE, etc.)
     */
    List<InventoryTransaction> findBySourceType(InventorySourceType sourceType);

    /**
     * Find transactions by source reference ID
     */
    List<InventoryTransaction> findBySourceId(UUID sourceId);
}

