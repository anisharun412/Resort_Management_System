package com.resortmanagement.system.marketing.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class PercentageStrategy implements PromotionStrategy {

    @Override
    public BigDecimal calculateDiscount(BigDecimal price, BigDecimal value) {
        if (price == null || value == null) {
            return BigDecimal.ZERO;
        }
        // value is percentage (e.g., 10 for 10%)
        return price.multiply(value).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}
