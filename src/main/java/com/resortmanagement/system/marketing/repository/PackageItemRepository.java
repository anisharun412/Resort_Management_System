package com.resortmanagement.system.marketing.repository;

import com.resortmanagement.system.marketing.entity.PackageItem;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PackageItemRepository
        extends com.resortmanagement.system.common.repository.SoftDeleteRepository<PackageItem, UUID> {
    org.springframework.data.domain.Page<PackageItem> findByDeletedFalse(
            org.springframework.data.domain.Pageable pageable);
}
