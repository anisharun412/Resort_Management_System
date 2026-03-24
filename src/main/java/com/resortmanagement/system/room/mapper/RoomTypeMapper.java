package com.resortmanagement.system.room.mapper;

import com.resortmanagement.system.room.dto.request.RoomTypeCreateRequest;
import com.resortmanagement.system.room.dto.request.RoomTypeUpdateRequest;
import com.resortmanagement.system.room.dto.response.RoomTypeResponse;
import com.resortmanagement.system.room.entity.RoomType;

public class RoomTypeMapper {

    private RoomTypeMapper() {}

    public static RoomTypeResponse toResponse(RoomType entity) {
        if (entity == null) return null;

        RoomTypeResponse res = new RoomTypeResponse();
        res.setId(entity.getId());
        res.setName(entity.getName());
        res.setBaseRate(entity.getBaseRate());
        res.setBedType(entity.getBedType());
        res.setAreaSqFt(entity.getAreaSqFt());
        res.setMaxOccupancy(entity.getMaxOccupancy());
        res.setAmenitiesSummary(entity.getAmenitiesSummary());
        res.setCreatedBy(entity.getCreatedBy());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedBy(entity.getUpdatedBy());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setDeleted(entity.isDeleted());
        res.setDeletedAt(entity.getDeletedAt());
        return res;
    }

    public static RoomType toEntity(RoomTypeCreateRequest request) {
        RoomType entity = new RoomType();
        entity.setName(request.getName());
        entity.setBaseRate(request.getBaseRate());
        entity.setBedType(request.getBedType());
        entity.setAreaSqFt(request.getAreaSqFt());
        entity.setMaxOccupancy(request.getMaxOccupancy());
        entity.setAmenitiesSummary(request.getAmenitiesSummary());
        return entity;
    }

    public static void updateEntity(RoomTypeUpdateRequest request, RoomType entity) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getBaseRate() != null) entity.setBaseRate(request.getBaseRate());
        if (request.getBedType() != null) entity.setBedType(request.getBedType());
        if (request.getAreaSqFt() != null) entity.setAreaSqFt(request.getAreaSqFt());
        if (request.getMaxOccupancy() != null) entity.setMaxOccupancy(request.getMaxOccupancy());
        if (request.getAmenitiesSummary() != null) entity.setAmenitiesSummary(request.getAmenitiesSummary());
    }
}
