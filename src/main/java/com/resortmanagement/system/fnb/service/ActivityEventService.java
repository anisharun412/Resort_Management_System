package com.resortmanagement.system.fnb.service;

import com.resortmanagement.system.fnb.entity.ActivityEvent;
import com.resortmanagement.system.fnb.repository.ActivityEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActivityEventService {

    private final ActivityEventRepository repository;
    private final com.resortmanagement.system.fnb.mapper.ActivityEventMapper mapper;

    public ActivityEventService(ActivityEventRepository repository, com.resortmanagement.system.fnb.mapper.ActivityEventMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<com.resortmanagement.system.fnb.dto.response.ActivityEventResponse> findAllActive() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }
    
    public List<com.resortmanagement.system.fnb.dto.response.ActivityEventResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    public Optional<com.resortmanagement.system.fnb.dto.response.ActivityEventResponse> findById(UUID id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public com.resortmanagement.system.fnb.dto.response.ActivityEventResponse create(com.resortmanagement.system.fnb.dto.request.ActivityEventRequest request) {
        ActivityEvent entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public com.resortmanagement.system.fnb.dto.response.ActivityEventResponse update(UUID id, com.resortmanagement.system.fnb.dto.request.ActivityEventRequest request) {
        ActivityEvent entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity event not found: " + id));
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Transactional
    public void delete(UUID id) {
        // Assuming soft delete if supported, but repo method was softDeleteById in view?
        // Checking view_file output from step 254: repository.softDeleteById(id);
        repository.softDeleteById(id, java.time.Instant.now());
    }
}
