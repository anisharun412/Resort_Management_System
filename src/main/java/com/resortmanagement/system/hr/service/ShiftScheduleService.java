package com.resortmanagement.system.hr.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.hr.entity.ShiftSchedule;
import com.resortmanagement.system.hr.repository.ShiftScheduleRepository;

@Service
public class ShiftScheduleService {

    private final ShiftScheduleRepository repository;

    public ShiftScheduleService(ShiftScheduleRepository repository) {
        this.repository = repository;
    }

    public org.springframework.data.domain.Page<ShiftSchedule> findAll(
            org.springframework.data.domain.Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<ShiftSchedule> findById(UUID id) {
        return repository.findById(id);
    }

    public ShiftSchedule save(ShiftSchedule entity) {
        if (entity.getEmployee() == null) {
            throw new IllegalArgumentException("Employee is required");
        }
        if (entity.getStartTime().isAfter(entity.getEndTime())) {
            throw new IllegalArgumentException("Start time cannot be after end time");
        }
        return repository.save(entity);
    }

    public ShiftSchedule update(UUID id, ShiftSchedule entity) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setEmployee(entity.getEmployee());
                    existing.setStartTime(entity.getStartTime());
                    existing.setEndTime(entity.getEndTime());
                    existing.setPosition(entity.getPosition());
                    existing.setLocation(entity.getLocation());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("ShiftSchedule not found with id " + id));
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("ShiftSchedule not found with id " + id);
        }
        repository.deleteById(id);
    }
}
