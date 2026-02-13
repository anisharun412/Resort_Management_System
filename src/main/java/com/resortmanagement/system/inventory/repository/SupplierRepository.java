package com.resortmanagement.system.inventory.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.resortmanagement.system.common.repository.SoftDeleteRepository;
import com.resortmanagement.system.inventory.entity.Supplier;

@Repository
public interface SupplierRepository extends SoftDeleteRepository<Supplier, UUID> {

    Optional<Supplier> findByName(String name);
}
