package com.resortmanagement.system.room.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.resortmanagement.system.common.repository.SoftDeleteRepository;
import com.resortmanagement.system.room.entity.Amenity;

@Repository
public interface AmenityRepository extends SoftDeleteRepository<Amenity, UUID> {
}
