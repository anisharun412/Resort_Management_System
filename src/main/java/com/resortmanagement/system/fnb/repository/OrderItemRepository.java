package com.resortmanagement.system.fnb.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.common.repository.SoftDeleteRepository;
import com.resortmanagement.system.fnb.entity.OrderItem;

@Repository
public interface OrderItemRepository extends SoftDeleteRepository<OrderItem, UUID> {

    @Query("select oi from OrderItem oi where oi.deletedAt is null")
    List<OrderItem> findAllActive();

}

