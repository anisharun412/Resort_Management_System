/*
TODO: EmployeeController.java
Purpose:
 - CRUD and lookup for employees.
Endpoints:
 - POST /api/v1/employees -> create employee (secure)
 - GET /api/v1/employees/{id}
 - PUT /api/v1/employees/{id}
 - GET /api/v1/employees?role=...
Responsibilities:
 - Do not return sensitive fields (password hash).
 - Use EmployeeService; map to DTOs.
File: hr/controller/EmployeeController.java
*/
package com.resortmanagement.system.hr.controller;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resortmanagement.system.hr.entity.Employee;
import com.resortmanagement.system.hr.service.EmployeeService;

@RestController
@RequestMapping("/api/hr/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<Employee>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity
                .ok(this.employeeService.findAll(org.springframework.data.domain.PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable UUID id) {
        return this.employeeService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee entity) {
        if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.employeeService.save(entity));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Employee>> getAvailableEmployees(@RequestParam Instant start,
            @RequestParam Instant end) {
        return ResponseEntity.ok(this.employeeService.findAvailableEmployees(start, end));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable UUID id, @RequestBody Employee entity) {
        return ResponseEntity.ok(this.employeeService.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
