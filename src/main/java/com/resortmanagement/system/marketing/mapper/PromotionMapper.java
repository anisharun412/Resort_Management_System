package com.resortmanagement.system.marketing.mapper;

import org.springframework.stereotype.Component;
import com.resortmanagement.system.marketing.dto.promotion.PromotionRequest;
import com.resortmanagement.system.marketing.dto.promotion.PromotionResponse;
import com.resortmanagement.system.marketing.entity.Promotion;

@Component
public class PromotionMapper {

    /**
     * Convert Promotion entity to PromotionResponse DTO
     */
    public PromotionResponse toResponse(Promotion entity) {
        if (entity == null) {
            return null;
        }

        return PromotionResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .description(entity.getDescription())
                .discountType(entity.getDiscountType())
                .value(entity.getValue())
                .validFrom(entity.getValidFrom())
                .validTo(entity.getValidTo())
                .usageLimit(entity.getUsageLimit())
                .terms(entity.getTerms())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .deleted(entity.isDeleted())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    /**
     * Convert PromotionRequest DTO to Promotion entity
     */
    public Promotion toEntity(PromotionRequest request) {
        if (request == null) {
            return null;
        }

        return Promotion.builder()
                .code(request.getCode())
                .description(request.getDescription())
                .discountType(request.getDiscountType())
                .value(request.getValue())
                .validFrom(request.getValidFrom())
                .validTo(request.getValidTo())
                .usageLimit(request.getUsageLimit())
                .terms(request.getTerms())
                .build();
    }

    /**
     * Update existing Promotion entity from PromotionRequest DTO
     */
    public void updateEntity(Promotion entity, PromotionRequest request) {
        if (entity == null || request == null) {
            return;
        }

        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setDiscountType(request.getDiscountType());
        entity.setValue(request.getValue());
        entity.setValidFrom(request.getValidFrom());
        entity.setValidTo(request.getValidTo());
        entity.setUsageLimit(request.getUsageLimit());
        entity.setTerms(request.getTerms());
    }
}
