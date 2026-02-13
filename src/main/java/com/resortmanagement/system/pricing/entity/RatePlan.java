/*
RatePlan.java
Purpose:
 - Rate plan that ties base price to room types and defines cancellation policy.
Fields:
 - id UUID
 - name String
 - description String
 - basePrice BigDecimal
 - cancellationPolicy String (or structured)
 - isRefundable boolean
 - minStayNights int
 - maxStayNights int
 - validFrom, validTo optional
 - extends Auditable
Notes:
 - Pricing calculation uses RateHistory + RatePlan + promotions -> ReservationDailyRate created by BookingService.
File: pricing/entity/RatePlan.java
*/
package com.resortmanagement.system.pricing.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.resortmanagement.system.common.audit.AuditableSoftDeletable;
import com.resortmanagement.system.room.entity.RoomType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rate_plan")
public class RatePlan extends AuditableSoftDeletable {
    @Id
    @UuidGenerator
    @Column(name = "rate_plan_id", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="room_type_id", nullable = false)
    private RoomType roomTypeId; // Optional direct link to RoomType for quick access

    @Column(name="base_price", nullable = false)
    private Double basePrice;

    @Column(name="cancellation_policy", length = 1000)
    private String cancellationPolicy; // e.g. "Free cancellation until 24h before check-in"
    
    @Column(name="is_refundable")
    private Boolean isRefundable;

    @Column(name="min_stay_nights")
    private Integer minStayNights;

    @Column(name="max_stay_nights")
    private Integer maxStayNights;

    @Column(name="valid_from")
    private LocalDate validFrom;

    @Column(name="valid_to")
    private LocalDate validTo;
}