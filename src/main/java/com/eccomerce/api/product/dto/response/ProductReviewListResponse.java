package com.eccomerce.api.product.dto.response;

import com.eccomerce.api.product.domain.ProductReview;

import java.util.List;

public record ProductReviewListResponse(
    Long totalCount,
    Double score,
    Long cursor,
    List<ProductReviewResponse> reviews
) {
    public static ProductReviewListResponse of(Long totalCount, Double score, Long cursor, List<ProductReview> reviews) {
        return new ProductReviewListResponse(
                totalCount,
                score,
                cursor,
                reviews.stream().map(ProductReviewResponse::from).toList()
        );
    }
}
