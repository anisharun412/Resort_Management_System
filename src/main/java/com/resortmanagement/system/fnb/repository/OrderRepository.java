package com.resortmanagement.system.fnb.repository;

import com.resortmanagement.system.fnb.entity.Order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import com.resortmanagement.system.common.repository.SoftDeleteRepository;

@Repository
public interface OrderRepository extends SoftDeleteRepository<Order, UUID> {

    /**
     * Fetch only active (not soft-deleted) orders
     */
    @Query("select o from Order o where o.deletedAt is null")
    List<Order> findAllActive();

}
