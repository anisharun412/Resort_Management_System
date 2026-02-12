package com.resortmanagement.system.fnb.repository;

import com.resortmanagement.system.fnb.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    /**
     * Fetch only active (not soft-deleted) orders
     */
    @Query("select o from Order o where o.deletedAt is null")
    List<Order> findAllActive();

        /**
        * Fetch only active (not soft-deleted) order by ID
        */
    @Query("select o from Order o where o.id = :id and o.deletedAt is null")
    Optional<Order> findByIdAndDeletedFalse(@Param("id") UUID id);
    /**
     * Soft delete order
     */
    @Modifying
    @Query("update Order o set o.deletedAt = CURRENT_TIMESTAMP where o.id = :id")
    void softDeleteById(@Param("id") UUID id);
}
