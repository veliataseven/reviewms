package com.va.reviewms.review.service;

import com.va.reviewms.review.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll(Long companyId);

    Review createReview(Long companyId, Review review);

    Review findById(Long reviewId);

    Review updateReview(Long reviewId, Review review);

    Optional<Review> deleteReview(Long reviewId);
}
