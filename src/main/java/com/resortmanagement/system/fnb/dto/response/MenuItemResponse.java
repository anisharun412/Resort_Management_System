package com.resortmanagement.system.fnb.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemResponse {
    private UUID id;
    private UUID menuId;
    private String menuName;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isAvailable;
    private List<MenuItemIngredientResponse> ingredients;
    // Audit fields (AuditableSoftDeletable)
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
