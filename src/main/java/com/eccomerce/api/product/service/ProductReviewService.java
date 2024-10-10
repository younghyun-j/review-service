package com.eccomerce.api.product.service;

import com.eccomerce.api.product.dto.request.CreateProductReviewRequest;
import com.eccomerce.api.product.dto.response.ProductReviewListResponse;
import org.springframework.data.domain.Pageable;

public interface ProductReviewService {
    void createProductReview(Long productId, CreateProductReviewRequest request, String imageUrl);
    ProductReviewListResponse findProductReviews(Long productId, Long cursor, Pageable pageable);
}
