package com.eccomerce.api.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Product")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reviewCount;
    private Double score;
    @OneToMany(mappedBy = "product")
    private List<ProductReview> reviews = new ArrayList<>();

    public void updateReviewCountAndRating(int newRating) {
        double updatedTotalRatings = this.score + newRating;
        this.reviewCount++;
        this.score = updatedTotalRatings / this.reviewCount;
    }
}
