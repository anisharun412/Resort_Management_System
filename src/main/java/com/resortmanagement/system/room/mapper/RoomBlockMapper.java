package com.resortmanagement.system.room.mapper;

import java.util.List;

import com.resortmanagement.system.room.dto.request.RoomBlockCreateRequest;
import com.resortmanagement.system.room.dto.request.RoomBlockUpdateRequest;
import com.resortmanagement.system.room.dto.response.RoomBlockResponse;
import com.resortmanagement.system.room.entity.MaintenanceRequest;
import com.resortmanagement.system.room.entity.Room;
import com.resortmanagement.system.room.entity.RoomBlock;

public class RoomBlockMapper {

    private RoomBlockMapper() {}

    public static RoomBlock toEntity(RoomBlockCreateRequest dto) {
        RoomBlock entity = new RoomBlock();
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setReason(dto.getReason());
        entity.setStatus(dto.getStatus());

        if (dto.getRoomId() != null) {
            Room room = new Room();
            room.setId(dto.getRoomId());
            entity.setRoom(room);
        }

        if (dto.getMaintenanceRequestId() != null) {
            MaintenanceRequest mr = new MaintenanceRequest();
            mr.setId(dto.getMaintenanceRequestId());
            entity.setMaintenanceRequest(mr);
        }

        return entity;
    }

    public static void updateEntity(RoomBlock entity, RoomBlockUpdateRequest dto) {
        if (dto.getStartDate() != null) entity.setStartDate(dto.getStartDate());
        if (dto.getEndDate() != null) entity.setEndDate(dto.getEndDate());
        if (dto.getReason() != null) entity.setReason(dto.getReason());
        if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
    }

    public static RoomBlockResponse toResponse(RoomBlock entity) {
        if (entity == null) return null;

        RoomBlockResponse res = new RoomBlockResponse();
        res.setId(entity.getId());
        res.setRoomId(entity.getRoom() != null ? entity.getRoom().getId() : null);
        res.setMaintenanceRequestId(entity.getMaintenanceRequest() != null ? entity.getMaintenanceRequest().getId() : null);
        res.setStartDate(entity.getStartDate());
        res.setEndDate(entity.getEndDate());
        res.setReason(entity.getReason());
        res.setStatus(entity.getStatus());

        res.setCreatedBy(entity.getCreatedBy());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedBy(entity.getUpdatedBy());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setDeleted(entity.isDeleted());
        res.setDeletedAt(entity.getDeletedAt());

        return res;
    }

    public static List<RoomBlockResponse> toResponseList(List<RoomBlock> list) {
        return list.stream().map(RoomBlockMapper::toResponse).toList();
    }
}
