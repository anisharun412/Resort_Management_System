package com.resortmanagement.system.marketing.mapper;

import org.springframework.stereotype.Component;
import com.resortmanagement.system.marketing.dto.packagedto.PackageRequest;
import com.resortmanagement.system.marketing.dto.packagedto.PackageResponse;
import com.resortmanagement.system.marketing.entity.Package;

@Component
public class PackageMapper {

    /**
     * Convert Package entity to PackageResponse DTO
     */
    public PackageResponse toResponse(Package entity) {
        if (entity == null) {
            return null;
        }

        return PackageResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .validFrom(entity.getValidFrom())
                .validTo(entity.getValidTo())
                .usageLimit(entity.getUsageLimit())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .deleted(entity.isDeleted())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    /**
     * Convert PackageRequest DTO to Package entity
     */
    public Package toEntity(PackageRequest request) {
        if (request == null) {
            return null;
        }

        return Package.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .validFrom(request.getValidFrom())
                .validTo(request.getValidTo())
                .usageLimit(request.getUsageLimit())
                .build();
    }

    /**
     * Update existing Package entity from PackageRequest DTO
     */
    public void updateEntity(Package entity, PackageRequest request) {
        if (entity == null || request == null) {
            return;
        }

        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setValidFrom(request.getValidFrom());
        entity.setValidTo(request.getValidTo());
        entity.setUsageLimit(request.getUsageLimit());
    }
}
