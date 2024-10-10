package com.eccomerce.api.product.dto.response;

import java.util.List;

public record ProductReviewResponse(
    int totalCount,
    int score,
    int cursor,
    List<ProductReviewDetailResponse> reviews
) {
    public static ProductReviewResponse of(int totalCount, int score, int cursor, List<ProductReviewDetailResponse> reviews) {
        return new ProductReviewResponse(
                totalCount,
                score,
                cursor,
                reviews
        );
    }
}
