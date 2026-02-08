/*
TODO: ShiftScheduleController.java
Purpose:
 - Manage shift assignments for staff across departments.
Endpoints:
 - POST /api/v1/shifts -> create shift assignment
 - GET /api/v1/employees/{id}/shifts
Responsibilities:
 - Validate overlapping shifts and employee availability.
 - Use ShiftScheduleService for conflict detection and persistence.

File: hr/controller/ShiftScheduleController.java
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

import com.resortmanagement.system.hr.entity.ShiftSchedule;
import com.resortmanagement.system.hr.service.ShiftScheduleService;

@RestController
@RequestMapping("/api/hr/shift_schedules")
public class ShiftScheduleController {

    private final ShiftScheduleService shiftScheduleService;

    public ShiftScheduleController(ShiftScheduleService shiftScheduleService) {
        this.shiftScheduleService = shiftScheduleService;
    }

    @GetMapping
    public ResponseEntity<Page<ShiftSchedule>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.shiftScheduleService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftSchedule> getById(@PathVariable UUID id) {
        return this.shiftScheduleService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShiftSchedule> create(@RequestBody ShiftSchedule entity) {
        if (entity.getEmployee() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.shiftScheduleService.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShiftSchedule> update(@PathVariable UUID id, @RequestBody ShiftSchedule entity) {
        return ResponseEntity.ok(this.shiftScheduleService.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.shiftScheduleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
