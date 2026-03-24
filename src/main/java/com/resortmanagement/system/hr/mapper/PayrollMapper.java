package com.resortmanagement.system.hr.mapper;

import org.springframework.stereotype.Component;
import com.resortmanagement.system.hr.dto.payroll.PayrollRequest;
import com.resortmanagement.system.hr.dto.payroll.PayrollResponse;
import com.resortmanagement.system.hr.entity.Payroll;
import com.resortmanagement.system.hr.entity.Employee;
import com.resortmanagement.system.hr.repository.EmployeeRepository;

import java.util.UUID;

@Component
public class PayrollMapper {

    private final EmployeeRepository employeeRepository;

    public PayrollMapper(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Convert Payroll entity to PayrollResponse DTO
     */
    public PayrollResponse toResponse(Payroll entity) {
        if (entity == null) {
            return null;
        }

        UUID employeeId = null;
        String employeeName = null;

        if (entity.getEmployee() != null) {
            employeeId = entity.getEmployee().getId();
            employeeName = entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName();
        }

        return PayrollResponse.builder()
                .id(entity.getId())
                .employeeId(employeeId)
                .employeeName(employeeName)
                .periodStart(entity.getPeriodStart())
                .periodEnd(entity.getPeriodEnd())
                .grossPay(entity.getGrossPay())
                .deductions(entity.getDeductions())
                .netPay(entity.getNetPay())
                .paidAt(entity.getPaidAt())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .deleted(entity.isDeleted())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    /**
     * Convert PayrollRequest DTO to Payroll entity
     * Fetches Employee entity from repository using employeeId
     */
    public Payroll toEntity(PayrollRequest request) {
        if (request == null) {
            return null;
        }

        Employee employee = null;
        if (request.getEmployeeId() != null) {
            employee = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Employee not found with id: " + request.getEmployeeId()));
        }

        return Payroll.builder()
                .employee(employee)
                .periodStart(request.getPeriodStart())
                .periodEnd(request.getPeriodEnd())
                .grossPay(request.getGrossPay())
                .deductions(request.getDeductions())
                .netPay(request.getNetPay())
                .paidAt(request.getPaidAt())
                .build();
    }

    /**
     * Update existing Payroll entity from PayrollRequest DTO
     */
    public void updateEntity(Payroll entity, PayrollRequest request) {
        if (entity == null || request == null) {
            return;
        }

        if (request.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Employee not found with id: " + request.getEmployeeId()));
            entity.setEmployee(employee);
        }

        entity.setPeriodStart(request.getPeriodStart());
        entity.setPeriodEnd(request.getPeriodEnd());
        entity.setGrossPay(request.getGrossPay());
        entity.setDeductions(request.getDeductions());
        entity.setNetPay(request.getNetPay());
        entity.setPaidAt(request.getPaidAt());
    }
}
