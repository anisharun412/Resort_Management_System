package com.resortmanagement.system.marketing.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.marketing.entity.Promotion;
import com.resortmanagement.system.marketing.repository.PromotionRepository;
import com.resortmanagement.system.marketing.strategy.PromotionStrategy;
import com.resortmanagement.system.marketing.strategy.PromotionStrategyFactory;

@Service
public class PromotionService {

    private final PromotionRepository repository;
    private final PromotionStrategyFactory strategyFactory;

    public PromotionService(PromotionRepository repository, PromotionStrategyFactory strategyFactory) {
        this.repository = repository;
        this.strategyFactory = strategyFactory;
    }

    public org.springframework.data.domain.Page<Promotion> findAll(org.springframework.data.domain.Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Promotion> findById(UUID id) {
        return repository.findById(id);
    }

    public Promotion update(UUID id, Promotion entity) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setCode(entity.getCode());
                    existing.setDescription(entity.getDescription());
                    existing.setDiscountType(entity.getDiscountType());
                    existing.setValue(entity.getValue());
                    existing.setValidFrom(entity.getValidFrom());
                    existing.setValidTo(entity.getValidTo());
                    existing.setUsageLimit(entity.getUsageLimit());
                    existing.setTerms(entity.getTerms());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Promotion not found with id " + id));
    }

    public Promotion save(Promotion entity) {
        if (entity.getCode() == null || entity.getCode().isEmpty()) {
            throw new IllegalArgumentException("Promotion code is required");
        }
        if (entity.getValidFrom() != null && entity.getValidTo() != null &&
                entity.getValidFrom().isAfter(entity.getValidTo())) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        // Check for duplicate code if new
        // Note: Repository needs findByCode to do this properly, adding if exists logic
        // later or relying on DB constraint
        return repository.save(entity);
    }

    public void deleteById(UUID id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }

    public BigDecimal calculateDiscount(UUID promotionId, BigDecimal originalPrice) {
        return findById(promotionId)
                .map(promo -> {
                    PromotionStrategy strategy = strategyFactory.getStrategy(promo.getDiscountType());
                    return strategy.calculateDiscount(originalPrice, promo.getValue());
                })
                .orElse(BigDecimal.ZERO);
    }
}
