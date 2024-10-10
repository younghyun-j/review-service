package com.eccomerce.api.product.repository;

import com.eccomerce.api.product.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    Boolean existsProductReviewByProductIdAndUserId(Long productId, Long userId);
}
