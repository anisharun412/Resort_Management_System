package com.resortmanagement.system.hr.service;

import java.time.Instant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.hr.entity.Employee;
import com.resortmanagement.system.hr.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public org.springframework.data.domain.Page<Employee> findAll(org.springframework.data.domain.Pageable pageable) {
        return repository.findByDeletedFalse(pageable);
    }

    public Optional<Employee> findById(UUID id) {
        // TODO: add caching and error handling
        return repository.findByIdAndDeletedFalse(id);
    }

    public Employee save(Employee entity) {
        if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        // Check for duplicate email if creating new
        if (entity.getId() == null && repository.findByEmail(entity.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        return repository.save(entity);
    }

    public Employee update(UUID id, Employee entity) {
        return repository.findByIdAndDeletedFalse(id)
                .map(existing -> {
                    existing.setFirstName(entity.getFirstName());
                    existing.setLastName(entity.getLastName());
                    existing.setEmail(entity.getEmail());
                    existing.setPhone(entity.getPhone());
                    // Department and Position are not in Employee entity, handled via Roles if
                    // needed
                    existing.setStatus(entity.getStatus());
                    existing.setHireDate(entity.getHireDate());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Employee not found with id " + id);
        }
        repository.softDeleteById(id, Instant.now());
    }

    public List<Employee> findAvailableEmployees(Instant startTime, Instant endTime) {
        // Placeholder for complex availability logic (checking shifts, leaves, etc.)
        return repository.findByDeletedFalse().stream()
                .filter(e -> e.getStatus() == Employee.EmployeeStatus.ACTIVE)
                .toList();
    }
}
