package com.resortmanagement.system.fnb.mapper;

import org.springframework.stereotype.Component;

import com.resortmanagement.system.fnb.dto.request.MenuRequest;
import com.resortmanagement.system.fnb.dto.response.MenuResponse;
import com.resortmanagement.system.fnb.entity.Menu;

@Component
public class MenuMapper {

    public Menu toEntity(MenuRequest request) {
        if (request == null) {
            return null;
        }
        Menu entity = new Menu();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public MenuResponse toResponse(Menu entity) {
        if (entity == null) {
            return null;
        }
        MenuResponse response = new MenuResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setCreatedBy(entity.getCreatedBy());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedBy(entity.getUpdatedBy());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setDeleted(entity.isDeleted());
        response.setDeletedAt(entity.getDeletedAt());
        return response;
    }

    public void updateEntity(Menu entity, MenuRequest request) {
        if (entity == null || request == null) {
            return;
        }
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
    }
}
