package com.resortmanagement.system.hr.repository;

import com.resortmanagement.system.common.repository.SoftDeleteRepository;
import com.resortmanagement.system.hr.entity.Payroll;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PayrollRepository extends SoftDeleteRepository<Payroll, UUID> {
    java.util.List<Payroll> findByEmployeeId(UUID employeeId);
}
