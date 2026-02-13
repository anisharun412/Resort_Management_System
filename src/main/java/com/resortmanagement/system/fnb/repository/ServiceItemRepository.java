package com.resortmanagement.system.fnb.repository;

import com.resortmanagement.system.common.repository.SoftDeleteRepository;
import com.resortmanagement.system.fnb.entity.ServiceItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceItemRepository extends SoftDeleteRepository<ServiceItem, UUID> {

    /**
     * Fetch only active (not soft-deleted) service items
     */
    @Query("select s from ServiceItem s where s.deletedAt is null")
    List<ServiceItem> findAllActive();

}
