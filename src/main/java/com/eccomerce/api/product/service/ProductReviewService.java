package com.eccomerce.api.product.service;

import com.eccomerce.api.product.dto.request.CreateProductReviewRequest;

public interface ProductReviewService {
    void saveProductReview(Long productId, CreateProductReviewRequest request, String imageUrl);
}
