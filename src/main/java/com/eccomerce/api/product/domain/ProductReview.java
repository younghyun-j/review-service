package com.eccomerce.api.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Review", indexes = {
        @Index(name = "idx_product_id_review_id", columnList = "productId, id"),
        @Index(name = "idx_product_id_user_id", columnList = "productId, userId")
})
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int score;
    @Column(nullable = false)
    private String content;
    private String imageUrl;
    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Builder
    public ProductReview(int score, String content, String imageUrl, Long userId, Product product) {
        this.score = score;
        this.content = content;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.product = product;
    }
}
