package com.resortmanagement.system.hr.repository;

import com.resortmanagement.system.hr.entity.ShiftSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShiftScheduleRepository extends JpaRepository<ShiftSchedule, UUID> {
}
