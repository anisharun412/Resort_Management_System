package com.resortmanagement.system.marketing.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class FixedAmountStrategy implements PromotionStrategy {

    @Override
    public BigDecimal calculateDiscount(BigDecimal price, BigDecimal value) {
        if (price == null || value == null) {
            return BigDecimal.ZERO;
        }
        // value is the fixed amount (e.g., 50.00)
        return value.min(price); // Discount cannot exceed price
    }
}
