package com.resortmanagement.system.hr.dto.shiftschedule;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftScheduleResponse {
    private UUID id;
    private UUID employeeId;
    private String employeeName;
    private Instant startTime;
    private Instant endTime;
    private String position;
    private String location;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
    private boolean deleted;
    private Instant deletedAt;
}
