/*
TODO: HR repositories and services guidelines
Repositories:
 - Standard JpaRepository for each entity.

Services:
 - EmployeeService: create/modify/disable employees; optionally integrate with IdP.
 - ShiftScheduleService: enforce constraints and manage shifts.
 - PayrollService: generate payroll; mark paid via accounting integration.

File: hr/repository/<File>.java, hr/service/<File>.java
*/
package com.resortmanagement.system.hr.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resortmanagement.system.hr.entity.EmployeeRole;
import com.resortmanagement.system.hr.repository.EmployeeRoleRepository;

@Service
public class EmployeeRoleService {

    private final EmployeeRoleRepository repository;

    public EmployeeRoleService(EmployeeRoleRepository repository) {
        this.repository = repository;
    }

    public Page<EmployeeRole> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<EmployeeRole> findById(UUID id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public EmployeeRole save(EmployeeRole entity) {
        if (entity.getEmployee() == null) {
            throw new IllegalArgumentException("Employee is required");
        }
        if (entity.getRole() == null) {
            throw new IllegalArgumentException("Role is required");
        }
        return repository.save(entity);
    }

    public EmployeeRole update(UUID id, EmployeeRole entity) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setEmployee(entity.getEmployee());
                    existing.setRole(entity.getRole());
                    existing.setAssignedDate(entity.getAssignedDate());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("EmployeeRole not found with id " + id));
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("EmployeeRole not found with id " + id);
        }
        repository.deleteById(id);
    }
}
