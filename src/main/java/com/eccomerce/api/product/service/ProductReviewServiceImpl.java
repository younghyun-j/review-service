package com.eccomerce.api.product.service;

import com.eccomerce.api.exception.CustomException;
import com.eccomerce.api.exception.ExceptionCode;
import com.eccomerce.api.product.domain.Product;
import com.eccomerce.api.product.domain.ProductReview;
import com.eccomerce.api.product.dto.request.CreateProductReviewRequest;
import com.eccomerce.api.product.dto.response.ProductReviewListResponse;
import com.eccomerce.api.product.repository.ProductRepository;
import com.eccomerce.api.product.repository.ProductReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductReviewServiceImpl implements ProductReviewService {

    private final ProductRepository productRepository;
    private final ProductReviewRepository productReviewRepository;

    @Override
    @Transactional
    public void createProductReview(Long productId, CreateProductReviewRequest request, String uploadImageUrl) {
        // 상품 조회
        Product getProduct = findProductById(productId);

        // 리뷰 중복 체크
        if (productReviewRepository.existsProductReviewByProductIdAndUserId(getProduct.getId(), request.userId())) {
            throw new CustomException(ExceptionCode.DUPLICATE_REVIEW);
        }

        // 리뷰 등록
        ProductReview review = CreateProductReviewRequest.toEntity(request, getProduct, uploadImageUrl);
        productReviewRepository.save(review);

        // 상품 리뷰카운트, 평점 업데이트
        getProduct.updateReviewCountAndRating(review.getScore());
    }

    @Override
    public ProductReviewListResponse findProductReviews(Long productId, Long cursor, Pageable pageable) {
        Product getProduct = findProductById(productId);
        Slice<ProductReview> reviews = productReviewRepository.findProductReviewsByCursorId(productId, cursor, pageable);
        Long nextCursor = getNextCursor(reviews);
        return ProductReviewListResponse.of(getProduct.getReviewCount(), getProduct.getScore(), nextCursor, reviews.getContent().stream().toList());
    }

    private Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ExceptionCode.PRODUCT_NOT_FOUND));
    }

    private Long getNextCursor(Slice<ProductReview> reviews) {
        if(!reviews.hasNext()) return null;
        return reviews.stream()
                .map(ProductReview::getId)
                .reduce((first, second) -> second)
                .orElse(null);
    }
}
