package com.resortmanagement.system.hr.repository;

import org.springframework.stereotype.Repository;

import com.resortmanagement.system.common.repository.SoftDeleteRepository;
import com.resortmanagement.system.hr.entity.Employee;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends SoftDeleteRepository<Employee, UUID> {
    java.util.Optional<Employee> findByEmail(String email);
}
