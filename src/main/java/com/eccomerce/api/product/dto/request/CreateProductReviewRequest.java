package com.eccomerce.api.product.dto.request;

import com.eccomerce.api.product.domain.Product;
import com.eccomerce.api.product.domain.ProductReview;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

public record CreateProductReviewRequest(
        @NotNull(message = "회원 아이디를 입력해주세요.")
        Long userId,
        @Range(min = 1, max = 5, message = "평점은 1점부터 5점 사이로 입력해주세요.")
        int score,
        @NotBlank(message = "리뷰 내용은 1자 이상이여야 합니다.")
        String content,
        MultipartFile image
) {
        public static ProductReview toEntity(CreateProductReviewRequest request, Product product, String uploadImageUrl) {
                return ProductReview.builder()
                        .userId(request.userId())
                        .score(request.score())
                        .content(request.content())
                        .product(product)
                        .imageUrl(uploadImageUrl)
                        .build();

        }
}
