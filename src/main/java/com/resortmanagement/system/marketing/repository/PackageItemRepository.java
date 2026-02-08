package com.resortmanagement.system.marketing.repository;

import com.resortmanagement.system.marketing.entity.PackageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PackageItemRepository extends JpaRepository<PackageItem, UUID> {
}
