package com.resortmanagement.system.room.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.room.dto.request.AmenityCreateRequest;
import com.resortmanagement.system.room.dto.request.AmenityUpdateRequest;
import com.resortmanagement.system.room.dto.response.AmenityResponse;
import com.resortmanagement.system.room.entity.Amenity;
import com.resortmanagement.system.room.mapper.AmenityMapper;
import com.resortmanagement.system.room.repository.AmenityRepository;

@Service
public class AmenityService {

    private final AmenityRepository repo;
    private final AmenityMapper mapper;

    public AmenityService(AmenityRepository repo, AmenityMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public AmenityResponse create(AmenityCreateRequest request) {
        Amenity entity = mapper.toEntity(request);
        return mapper.toResponse(repo.save(entity));
    }

    public List<AmenityResponse> getAll() {
        return mapper.toResponseList(repo.findByDeletedFalse());
    }

    public AmenityResponse getById(UUID id) {
        return mapper.toResponse(
                repo.findByIdAndDeletedFalse(id).orElseThrow(() -> new RuntimeException("Amenity not found"))
        );
    }

    public AmenityResponse update(UUID id, AmenityUpdateRequest request) {

        Amenity entity = repo.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        mapper.update(entity, request);

        return mapper.toResponse(repo.save(entity));
    }

    public void delete(UUID id) {
        repo.softDeleteById(id, Instant.now());
    }
}
