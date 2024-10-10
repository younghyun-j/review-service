package com.eccomerce.api.product.controller;

import com.eccomerce.api.image.service.ImageService;
import com.eccomerce.api.product.dto.request.CreateProductReviewRequest;
import com.eccomerce.api.product.dto.response.ProductReviewListResponse;
import com.eccomerce.api.product.service.ProductReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductReviewController {

    private final ProductReviewService productReviewService;
    private final ImageService imageService;

    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<Void> createProductReview(@PathVariable Long productId,
                                            @ModelAttribute @Valid CreateProductReviewRequest request) {
        // 이미지 업로드
        String uploadImageUrl = "";
        if (request.image() != null) {
            uploadImageUrl = imageService.imageUpload(request.image());
        }
        productReviewService.createProductReview(productId, request, uploadImageUrl);
        return ResponseEntity.ok().build();
    }

}
