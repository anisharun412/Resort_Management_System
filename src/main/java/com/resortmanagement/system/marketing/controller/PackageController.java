/*
TODO: PackageController.java
Purpose:
 - Manage packages (room + services bundles).
Endpoints:
 - POST /api/v1/packages
 - GET /api/v1/packages
 - POST /api/v1/packages/{id}/book -> apply package as reservation-level operation
Responsibilities:
 - When booked, expand package into ReservationDailyRate + ReservationAddOn + ReservationServiceBooking via BookingService/factory.
File: marketing/controller/PackageController.java
*/
package com.resortmanagement.system.marketing.controller;

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

import com.resortmanagement.system.marketing.entity.Package;
import com.resortmanagement.system.marketing.service.PackageService;

@RestController
@RequestMapping("/api/marketing/packages")
public class PackageController {

    private final PackageService service;

    public PackageController(PackageService packageService) {
        this.service = packageService;
    }

    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<Package>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.service.findAll(org.springframework.data.domain.PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Package> getById(@PathVariable UUID id) {
        return this.service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Package> create(@RequestBody Package entity) {
        if (entity.getName() == null || entity.getPrice() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Package> update(@PathVariable UUID id, @RequestBody Package entity) {
        return ResponseEntity.ok(this.service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
