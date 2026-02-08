package com.resortmanagement.system.marketing.repository;

import com.resortmanagement.system.marketing.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, UUID> {
    java.util.Optional<Promotion> findByCode(String code);
}
