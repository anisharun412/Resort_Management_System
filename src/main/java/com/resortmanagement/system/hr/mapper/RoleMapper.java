package com.resortmanagement.system.hr.mapper;

import org.springframework.stereotype.Component;
import com.resortmanagement.system.hr.dto.role.RoleRequest;
import com.resortmanagement.system.hr.dto.role.RoleResponse;
import com.resortmanagement.system.hr.entity.Role;

@Component
public class RoleMapper {

    /**
     * Convert Role entity to RoleResponse DTO
     */
    public RoleResponse toResponse(Role entity) {
        if (entity == null) {
            return null;
        }

        return RoleResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .permissionsJson(entity.getPermissionsJson())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .deleted(entity.isDeleted())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    /**
     * Convert RoleRequest DTO to Role entity
     */
    public Role toEntity(RoleRequest request) {
        if (request == null) {
            return null;
        }

        return Role.builder()
                .name(request.getName())
                .description(request.getDescription())
                .permissionsJson(request.getPermissionsJson())
                .build();
    }

    /**
     * Update existing Role entity from RoleRequest DTO
     */
    public void updateEntity(Role entity, RoleRequest request) {
        if (entity == null || request == null) {
            return;
        }

        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPermissionsJson(request.getPermissionsJson());
    }
}
