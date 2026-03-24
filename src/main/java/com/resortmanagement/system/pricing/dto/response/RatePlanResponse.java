package com.resortmanagement.system.pricing.dto.response;

import java.time.Instant;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RatePlanResponse {

    private UUID id;
    private String name;
    private String description;
    private Double basePrice;
    private Boolean refundable;
    private Integer minStayNights;
    private Integer maxStayNights;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
