package com.resortmanagement.system.marketing.repository;

import com.resortmanagement.system.marketing.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PackageRepository extends JpaRepository<Package, UUID> {
}
