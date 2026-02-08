package com.resortmanagement.system.marketing.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.resortmanagement.system.marketing.entity.Promotion.DiscountType;

@Component
public class PromotionStrategyFactory {

    private final Map<String, PromotionStrategy> strategies;

    public PromotionStrategyFactory(Map<String, PromotionStrategy> strategies) {
        this.strategies = strategies;
    }

    public PromotionStrategy getStrategy(DiscountType type) {
        if (type == null) {
            throw new IllegalArgumentException("Discount type cannot be null");
        }

        return switch (type) {
            case PERCENT -> strategies.get("percentageStrategy");
            case AMOUNT -> strategies.get("fixedAmountStrategy");
            default -> throw new IllegalArgumentException("Unknown discount type: " + type);
        };
    }
}
