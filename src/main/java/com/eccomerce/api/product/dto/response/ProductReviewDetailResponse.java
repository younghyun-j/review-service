package com.eccomerce.api.product.dto.response;

import com.eccomerce.api.product.domain.ProductReview;

import java.time.LocalDateTime;

public record ProductReviewDetailResponse(
        Long id,
        Long userId,
        int score,
        String content,
        String imageUrl,
        LocalDateTime createdAt
) {
    public static ProductReviewDetailResponse from(ProductReview productReview) {
        return new ProductReviewDetailResponse(
                productReview.getId(),
                productReview.getUser().getId(),
                productReview.getScore(),
                productReview.getContent(),
                productReview.getImageUrl(),
                productReview.getCreatedAt()
        );
    }
}
