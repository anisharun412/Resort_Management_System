package com.resortmanagement.system.inventory.mapper;

import org.springframework.stereotype.Component;

import com.resortmanagement.system.inventory.dto.request.SupplierRequest;
import com.resortmanagement.system.inventory.dto.response.SupplierResponse;
import com.resortmanagement.system.inventory.entity.Supplier;

@Component
public class SupplierMapper {

    public Supplier toEntity(SupplierRequest request) {
        if (request == null) {
            return null;
        }
        Supplier entity = new Supplier();
        entity.setName(request.getName());
        entity.setContactPerson(request.getContactPerson());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setAddress(request.getAddress());
        return entity;
    }

    public SupplierResponse toResponse(Supplier entity) {
        if (entity == null) {
            return null;
        }
        SupplierResponse response = new SupplierResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setContactPerson(entity.getContactPerson());
        response.setPhone(entity.getPhone());
        response.setEmail(entity.getEmail());
        response.setAddress(entity.getAddress());
        response.setCreatedBy(entity.getCreatedBy());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedBy(entity.getUpdatedBy());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setDeleted(entity.isDeleted());
        response.setDeletedAt(entity.getDeletedAt());
        return response;
    }

    public void updateEntity(Supplier entity, SupplierRequest request) {
        if (entity == null || request == null) {
            return;
        }
        entity.setName(request.getName());
        entity.setContactPerson(request.getContactPerson());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setAddress(request.getAddress());
    }
}
