package com.resortmanagement.system.fnb.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.common.repository.SoftDeleteRepository;
import com.resortmanagement.system.fnb.entity.OrderItem;

@Repository
public interface OrderItemRepository extends SoftDeleteRepository<OrderItem, UUID> {

    @Query("select oi from OrderItem oi where oi.deletedAt is null")
    List<OrderItem> findAllActive();

    @Modifying
    @Query("update OrderItem oi set oi.deletedAt = CURRENT_TIMESTAMP where oi.id = :id")
    void softDeleteById(@Param("id") UUID id);
}

