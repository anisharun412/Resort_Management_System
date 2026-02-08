/*
TODO: PayrollController.java
Purpose:
 - Endpoints for payroll processing and payouts (admin only).
Endpoints:
 - POST /api/v1/payrolls/generate?period=yyyy-MM -> run payroll for period
 - GET /api/v1/payrolls/{id}
Responsibilities:
 - Payroll generation is heavy: implement as async job, produce reports, and store payroll records.
 - Keep payroll sensitive; restrict access.

File: hr/controller/PayrollController.java
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

import com.resortmanagement.system.hr.entity.Payroll;
import com.resortmanagement.system.hr.service.PayrollService;

@RestController
@RequestMapping("/api/hr/payrolls")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @GetMapping
    public ResponseEntity<Page<Payroll>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.payrollService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payroll> getById(@PathVariable UUID id) {
        return this.payrollService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Payroll> create(@RequestBody Payroll entity) {
        if (entity.getEmployee() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.payrollService.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payroll> update(@PathVariable UUID id, @RequestBody Payroll entity) {
        return ResponseEntity.ok(this.payrollService.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.payrollService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
