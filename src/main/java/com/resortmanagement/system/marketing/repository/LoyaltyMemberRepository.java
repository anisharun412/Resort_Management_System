package com.resortmanagement.system.marketing.repository;

import com.resortmanagement.system.marketing.entity.LoyaltyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoyaltyMemberRepository extends JpaRepository<LoyaltyMember, UUID> {
}
