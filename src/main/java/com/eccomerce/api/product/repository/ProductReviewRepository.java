package com.eccomerce.api.product.repository;

import com.eccomerce.api.product.domain.ProductReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    Boolean existsProductReviewByProductIdAndUserId(Long productId, Long userId);

    @Query("SELECT pr FROM ProductReview pr WHERE pr.product.id = :productId AND (:cursor IS NULL OR pr.id < :cursor) ORDER BY pr.id DESC")
    Slice<ProductReview> findProductReviewsByCursorId(Long productId, Long cursor, Pageable pageable);
}
