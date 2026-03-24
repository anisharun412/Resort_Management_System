package com.resortmanagement.system.room.mapper;

import java.util.List;

import com.resortmanagement.system.room.dto.request.HousekeepingTaskUpdateRequest;
import com.resortmanagement.system.room.dto.response.HousekeepingTaskResponse;
import com.resortmanagement.system.room.entity.HousekeepingTask;

public class HousekeepingTaskMapper {

    private HousekeepingTaskMapper(){}

    public static HousekeepingTaskResponse toResponse(HousekeepingTask task) {

        HousekeepingTaskResponse res = new HousekeepingTaskResponse();

        res.setId(task.getId());
        res.setRoomId(task.getRoom().getId());
        res.setStaffId(task.getStaff() != null ? task.getStaff().getId() : null);
        res.setScheduledAt(task.getScheduledAt());
        res.setPriority(task.getPriority());
        res.setStatus(task.getStatus());
        res.setNotes(task.getNotes());
        res.setCreatedBy(task.getCreatedBy());
        res.setCreatedAt(task.getCreatedAt());
        res.setUpdatedBy(task.getUpdatedBy());
        res.setUpdatedAt(task.getUpdatedAt());
        res.setDeleted(task.isDeleted());
        res.setDeletedAt(task.getDeletedAt());

        return res;
    }

    public static List<HousekeepingTaskResponse> toResponseList(List<HousekeepingTask> list) {
        return list.stream().map(HousekeepingTaskMapper::toResponse).toList();
    }

    public static void update(HousekeepingTask task, HousekeepingTaskUpdateRequest req) {

        if(req.getScheduledAt() != null)
            task.setScheduledAt(req.getScheduledAt());

        if(req.getPriority() != null)
            task.setPriority(req.getPriority());

        if(req.getStatus() != null)
            task.setStatus(req.getStatus());

        if(req.getNotes() != null)
            task.setNotes(req.getNotes());
    }
}
