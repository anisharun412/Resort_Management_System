package com.resortmanagement.system.marketing.repository;

import com.resortmanagement.system.marketing.entity.LoyaltyMember;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoyaltyMemberRepository
        extends com.resortmanagement.system.common.repository.SoftDeleteRepository<LoyaltyMember, UUID> {
    org.springframework.data.domain.Page<LoyaltyMember> findByDeletedFalse(
            org.springframework.data.domain.Pageable pageable);
}
