package com.resortmanagement.system.room.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.resortmanagement.system.room.dto.request.AmenityCreateRequest;
import com.resortmanagement.system.room.dto.request.AmenityUpdateRequest;
import com.resortmanagement.system.room.dto.response.AmenityResponse;
import com.resortmanagement.system.room.entity.Amenity;

@Component
public class AmenityMapper {

    public Amenity toEntity(AmenityCreateRequest req) {
        Amenity a = new Amenity();
        a.setName(req.getName());
        a.setDescription(req.getDescription());
        a.setCategory(req.getCategory());
        return a;
    }

    public void update(Amenity entity, AmenityUpdateRequest req) {
        entity.setName(req.getName());
        entity.setDescription(req.getDescription());
        entity.setCategory(req.getCategory());
    }

    public AmenityResponse toResponse(Amenity entity) {
        AmenityResponse res = new AmenityResponse();
        res.setId(entity.getId());
        res.setName(entity.getName());
        res.setDescription(entity.getDescription());
        res.setCategory(entity.getCategory());
        res.setCreatedBy(entity.getCreatedBy());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedBy(entity.getUpdatedBy());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setDeleted(entity.isDeleted());
        res.setDeletedAt(entity.getDeletedAt());
        return res;
    }

    public List<AmenityResponse> toResponseList(List<Amenity> list) {
        return list.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
