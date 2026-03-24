package com.resortmanagement.system.marketing.dto.promotion;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import com.resortmanagement.system.marketing.entity.Promotion.DiscountType;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionResponse {
    private UUID id;
    private String code;
    private String description;
    private DiscountType discountType;
    private BigDecimal value;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Integer usageLimit;
    private String terms;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
    private boolean deleted;
    private Instant deletedAt;
}
