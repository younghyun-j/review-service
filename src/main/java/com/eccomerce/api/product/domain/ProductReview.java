package com.eccomerce.api.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Review")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;
    private String content;
    private String imageUrl;
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
