package com.resortmanagement.system.hr.repository;

import com.resortmanagement.system.common.repository.SoftDeleteRepository;
import com.resortmanagement.system.hr.entity.EmployeeRole;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRoleRepository extends SoftDeleteRepository<EmployeeRole, UUID> {
    java.util.List<EmployeeRole> findByEmployeeId(UUID employeeId);
}
