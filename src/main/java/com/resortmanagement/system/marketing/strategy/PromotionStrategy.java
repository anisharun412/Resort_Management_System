package com.resortmanagement.system.marketing.strategy;

import java.math.BigDecimal;

public interface PromotionStrategy {
    BigDecimal calculateDiscount(BigDecimal price, BigDecimal value);
}
