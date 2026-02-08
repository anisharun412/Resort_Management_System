package com.resortmanagement.system.hr.repository;

import com.resortmanagement.system.hr.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, UUID> {
}
