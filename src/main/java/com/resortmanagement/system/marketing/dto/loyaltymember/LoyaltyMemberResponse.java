package com.resortmanagement.system.marketing.dto.loyaltymember;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import com.resortmanagement.system.marketing.entity.LoyaltyMember.MemberStatus;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyMemberResponse {
    private UUID id;
    private UUID guestId;
    private String guestName;
    private String tier;
    private BigDecimal pointsBalance;
    private Instant enrolledAt;
    private MemberStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
    private boolean deleted;
    private Instant deletedAt;
}
