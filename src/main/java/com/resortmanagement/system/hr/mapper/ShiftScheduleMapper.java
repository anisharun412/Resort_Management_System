package com.resortmanagement.system.hr.mapper;

import org.springframework.stereotype.Component;
import com.resortmanagement.system.hr.dto.shiftschedule.ShiftScheduleRequest;
import com.resortmanagement.system.hr.dto.shiftschedule.ShiftScheduleResponse;
import com.resortmanagement.system.hr.entity.ShiftSchedule;
import com.resortmanagement.system.hr.entity.Employee;
import com.resortmanagement.system.hr.repository.EmployeeRepository;

import java.util.UUID;

@Component
public class ShiftScheduleMapper {

    private final EmployeeRepository employeeRepository;

    public ShiftScheduleMapper(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Convert ShiftSchedule entity to ShiftScheduleResponse DTO
     */
    public ShiftScheduleResponse toResponse(ShiftSchedule entity) {
        if (entity == null) {
            return null;
        }

        UUID employeeId = null;
        String employeeName = null;

        if (entity.getEmployee() != null) {
            employeeId = entity.getEmployee().getId();
            employeeName = entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName();
        }

        return ShiftScheduleResponse.builder()
                .id(entity.getId())
                .employeeId(employeeId)
                .employeeName(employeeName)
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .position(entity.getPosition())
                .location(entity.getLocation())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .deleted(entity.isDeleted())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    /**
     * Convert ShiftScheduleRequest DTO to ShiftSchedule entity
     * Fetches Employee entity from repository using employeeId
     */
    public ShiftSchedule toEntity(ShiftScheduleRequest request) {
        if (request == null) {
            return null;
        }

        Employee employee = null;
        if (request.getEmployeeId() != null) {
            employee = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Employee not found with id: " + request.getEmployeeId()));
        }

        return ShiftSchedule.builder()
                .employee(employee)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .position(request.getPosition())
                .location(request.getLocation())
                .build();
    }

    /**
     * Update existing ShiftSchedule entity from ShiftScheduleRequest DTO
     */
    public void updateEntity(ShiftSchedule entity, ShiftScheduleRequest request) {
        if (entity == null || request == null) {
            return;
        }

        if (request.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Employee not found with id: " + request.getEmployeeId()));
            entity.setEmployee(employee);
        }

        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setPosition(request.getPosition());
        entity.setLocation(request.getLocation());
    }
}
