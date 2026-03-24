package com.resortmanagement.system.hr.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.hr.dto.EmployeeRoleDTO;
import com.resortmanagement.system.hr.dto.employee.EmployeeRequest;
import com.resortmanagement.system.hr.dto.employee.EmployeeResponse;
import com.resortmanagement.system.hr.entity.Employee;
import com.resortmanagement.system.hr.mapper.EmployeeMapper;
import com.resortmanagement.system.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final EmployeeRoleService employeeRoleService;

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> findAll(Pageable pageable) {
        return repository.findByDeletedFalse(pageable).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeResponse> findById(UUID id) {
        return repository.findByIdAndDeletedFalse(id).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Optional<Employee> findEmployeeById(UUID id) {
        return repository.findByIdAndDeletedFalse(id);
    }

    public EmployeeResponse save(EmployeeRequest dto) {
        if (dto.getFirstName() == null || dto.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (dto.getLastName() == null || dto.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        Employee employee = mapper.toEntity(dto);
        if(dto.getRoleId() != null){
            EmployeeRoleDTO employeeRoleDTO = new EmployeeRoleDTO();
            employeeRoleDTO.setEmployeeId(employee.getId());
            employeeRoleDTO.setRoleId(dto.getRoleId());
            employeeRoleDTO.setEmployeeName(employee.getFirstName()+" "+employee.getLastName());
            employeeRoleDTO.setAssignedDate(dto.getAssignedDate());
            employeeRoleDTO.setEndDate(dto.getEndDate());
            employeeRoleService.save(employeeRoleDTO);
        }
        Employee saved = repository.save(employee);
        return mapper.toResponse(saved);
    }

    public EmployeeResponse update(UUID id, EmployeeRequest dto) {
        return repository.findByIdAndDeletedFalse(id)
                .map(existing -> {
                    mapper.updateEntity(existing, dto);
                    if(dto.getRoleId() != null){
                        EmployeeRoleDTO employeeRoleDTO = new EmployeeRoleDTO();
                        employeeRoleDTO.setEmployeeId(existing.getId());
                        employeeRoleDTO.setRoleId(dto.getRoleId());
                        employeeRoleDTO.setEmployeeName(existing.getFirstName()+" "+existing.getLastName());
                        employeeRoleDTO.setAssignedDate(dto.getAssignedDate());
                        employeeRoleDTO.setEndDate(dto.getEndDate());
                        employeeRoleService.save(employeeRoleDTO);
                    }
                    return mapper.toResponse(repository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Employee not found with id " + id);
        }
        repository.softDeleteById(id, Instant.now());
    }

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> findAvailableEmployees(Pageable pageable) {
        return repository.findByStatusAndDeletedFalse(Employee.EmployeeStatus.ACTIVE, pageable)
                .map(mapper::toResponse);
    }
}
