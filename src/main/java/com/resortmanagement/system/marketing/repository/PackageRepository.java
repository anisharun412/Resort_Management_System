package com.resortmanagement.system.marketing.repository;

import com.resortmanagement.system.marketing.entity.Package;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PackageRepository
        extends com.resortmanagement.system.common.repository.SoftDeleteRepository<Package, UUID> {
    org.springframework.data.domain.Page<Package> findByDeletedFalse(org.springframework.data.domain.Pageable pageable);
}
