package com.eccomerce.api.product.dto.response;

import java.util.List;

public record ProductReviewListResponse(
    int totalCount,
    int score,
    int cursor,
    List<ProductReviewResponse> reviews
) {
    public static ProductReviewListResponse of(int totalCount, int score, int cursor, List<ProductReviewResponse> reviews) {
        return new ProductReviewListResponse(
                totalCount,
                score,
                cursor,
                reviews
        );
    }
}
