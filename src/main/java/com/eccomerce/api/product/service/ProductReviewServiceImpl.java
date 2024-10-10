package com.eccomerce.api.product.service;

import com.eccomerce.api.exception.CustomException;
import com.eccomerce.api.exception.ExceptionCode;
import com.eccomerce.api.product.domain.Product;
import com.eccomerce.api.product.domain.ProductReview;
import com.eccomerce.api.product.dto.request.CreateProductReviewRequest;
import com.eccomerce.api.product.repository.ProductRepository;
import com.eccomerce.api.product.repository.ProductReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductReviewServiceImpl implements ProductReviewService {

    private final ProductRepository productRepository;
    private final ProductReviewRepository productReviewRepository;

    @Override
    @Transactional
    public void saveProductReview(Long productId, CreateProductReviewRequest request, String uploadImageUrl) {
        // 상품 조회
        Product getProduct = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ExceptionCode.PRODUCT_NOT_FOUND));

        // 리뷰 중복 체크
        Boolean hasUserAlreadyReviewedProduct = productReviewRepository
                .existsProductReviewByProductIdAndUserId(getProduct.getId(), request.userId());
        if (hasUserAlreadyReviewedProduct) {
            throw new CustomException(ExceptionCode.DUPLICATE_REVIEW);
        }

        // 리뷰 등록
        ProductReview review = CreateProductReviewRequest.toEntity(request, getProduct, uploadImageUrl);
        productReviewRepository.save(review);

        // 상품 리뷰카운트, 평점 업데이트
        getProduct.updateReviewCountAndRating(review.getScore());
    }
}
