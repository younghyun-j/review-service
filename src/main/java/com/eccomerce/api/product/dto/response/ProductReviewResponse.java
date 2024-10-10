package com.eccomerce.api.product.dto.response;

import com.eccomerce.api.product.domain.ProductReview;

import java.time.LocalDateTime;

public record ProductReviewResponse(
        Long id,
        Long userId,
        int score,
        String content,
        String imageUrl
) {
    public static ProductReviewResponse from(ProductReview productReview) {
        return new ProductReviewResponse(
                productReview.getId(),
                productReview.getUserId(),
                productReview.getScore(),
                productReview.getContent(),
                productReview.getImageUrl()
        );
    }
}
