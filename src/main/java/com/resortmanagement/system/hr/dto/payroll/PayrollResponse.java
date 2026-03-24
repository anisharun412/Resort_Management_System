package com.resortmanagement.system.hr.dto.payroll;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayrollResponse {
    private UUID id;
    private UUID employeeId;
    private String employeeName; // Derived from Employee entity
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private BigDecimal grossPay;
    private BigDecimal deductions;
    private BigDecimal netPay;
    private Instant paidAt;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
    private boolean deleted;
    private Instant deletedAt;
}
