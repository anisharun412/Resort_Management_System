package com.resortmanagement.system.marketing.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.marketing.entity.Package;
import com.resortmanagement.system.marketing.repository.PackageRepository;

@Service
public class PackageService {

    private final PackageRepository repository;

    public PackageService(PackageRepository repository) {
        this.repository = repository;
    }

    public org.springframework.data.domain.Page<Package> findAll(org.springframework.data.domain.Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Package> findById(UUID id) {
        return repository.findById(id);
    }

    public Package update(UUID id, Package entity) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(entity.getName());
                    existing.setDescription(entity.getDescription());
                    existing.setPrice(entity.getPrice());
                    existing.setValidFrom(entity.getValidFrom());
                    existing.setValidTo(entity.getValidTo());
                    existing.setUsageLimit(entity.getUsageLimit());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Package not found with id " + id));
    }

    public Package save(Package entity) {
        if (entity.getName() == null || entity.getName().isEmpty()) {
            throw new IllegalArgumentException("Package name is required");
        }
        if (entity.getPrice() == null || entity.getPrice().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        return repository.save(entity);
    }

    public void deleteById(UUID id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
