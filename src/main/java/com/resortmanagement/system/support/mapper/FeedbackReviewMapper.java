package com.resortmanagement.system.support.mapper;

import com.resortmanagement.system.support.dto.response.FeedbackReviewResponse;
import com.resortmanagement.system.support.entity.FeedbackReview;

public class FeedbackReviewMapper {

    public static FeedbackReviewResponse toResponse(FeedbackReview review) {
        return new FeedbackReviewResponse(
                review.getId(),
                review.getGuest() != null ? review.getGuest().getId() : null,
                review.getReservation() != null ? review.getReservation().getId() : null,
                review.getRating(),
                review.getComments(),
                review.getResponseBy(),
                review.getRespondedAt(),
                review.getCreatedBy(),
                review.getCreatedAt(),
                review.getUpdatedBy(),
                review.getUpdatedAt(),
                review.isDeleted(),
                review.getDeletedAt()
        );
    }
}
