/*
TODO: RoleController.java
Purpose:
 - Manage roles & permissions; UI for role creation/permission assignment.
Endpoints:
 - POST /api/v1/roles
 - GET /api/v1/roles
 - PUT /api/v1/roles/{id}
Notes:
 - Permission logic should be a JSON/structure validated by an admin UI.
File: hr/controller/RoleController.java
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

import com.resortmanagement.system.hr.entity.Role;
import com.resortmanagement.system.hr.service.RoleService;

@RestController
@RequestMapping("/api/hr/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Page<Role>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.roleService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable UUID id) {
        return this.roleService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role entity) {
        if (entity.getName() == null || entity.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.roleService.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable UUID id, @RequestBody Role entity) {
        return ResponseEntity.ok(this.roleService.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
