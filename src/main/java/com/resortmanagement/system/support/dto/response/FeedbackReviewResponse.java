package com.resortmanagement.system.support.dto.response;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FeedbackReviewResponse {

    private UUID id;
    private UUID guestId;
    private UUID reservationId;
    private Integer rating;
    private String comments;
    private UUID responseBy;
    private LocalDateTime respondedAt;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
