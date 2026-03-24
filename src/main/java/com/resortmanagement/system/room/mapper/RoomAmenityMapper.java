package com.resortmanagement.system.room.mapper;

import com.resortmanagement.system.room.dto.response.RoomAmenityResponse;
import com.resortmanagement.system.room.entity.RoomAmenity;

public class RoomAmenityMapper {

    private RoomAmenityMapper() {}

    public static RoomAmenityResponse toResponse(RoomAmenity entity) {
        RoomAmenityResponse res = new RoomAmenityResponse();

        res.setId(entity.getId());
        res.setRoomId(entity.getRoom().getId());
        res.setAmenityId(entity.getAmenity().getId());
        res.setComplimentary(entity.getComplimentary());
        res.setCreatedBy(entity.getCreatedBy());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedBy(entity.getUpdatedBy());
        res.setUpdatedAt(entity.getUpdatedAt());

        return res;
    }
}
