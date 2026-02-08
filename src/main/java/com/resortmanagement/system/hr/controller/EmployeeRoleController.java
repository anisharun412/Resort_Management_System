/*
TODO: EmployeeRoleController.java
Purpose:
 - Manage role assignments, roles list.
Endpoints:
 - GET /api/v1/roles
 - POST /api/v1/employees/{id}/roles -> assign role
Responsibilities:
 - Use RoleService and EmployeeRoleService for transactional operations.
File: hr/controller/EmployeeRoleController.java
*/
package com.resortmanagement.system.hr.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.resortmanagement.system.hr.entity.EmployeeRole;
import com.resortmanagement.system.hr.service.EmployeeRoleService;

@RestController
@RequestMapping("/api/hr/employee_roles")
public class EmployeeRoleController {

    private final EmployeeRoleService service;

    public EmployeeRoleController(EmployeeRoleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeRole>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.service.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRole> getById(@PathVariable UUID id) {
        return this.service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmployeeRole> create(@RequestBody EmployeeRole entity) {
        if (entity.getEmployee() == null || entity.getRole() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeRole> update(@PathVariable UUID id, @RequestBody EmployeeRole entity) {
        return ResponseEntity.ok(this.service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
