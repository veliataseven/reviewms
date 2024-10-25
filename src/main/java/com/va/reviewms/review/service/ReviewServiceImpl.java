package com.va.reviewms.review.service;

import com.va.reviewms.review.model.Review;
import com.va.reviewms.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAll(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review createReview(Long companyId, Review review) {
        if (companyId != null) {
            review.setCompanyId(companyId);
            return reviewRepository.save(review);
        } else throw new IllegalArgumentException("Company not found");
    }

    @Override
    public Review findById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            return review;
        } else throw new IllegalArgumentException("Review not found");
    }

    @Override
    public Review updateReview(Long reviewId, Review review) {
        Review reviewToBeUpdated = reviewRepository.findById(reviewId).orElse(null);
        if (reviewToBeUpdated != null) {
            reviewToBeUpdated.setTitle(review.getTitle());
            reviewToBeUpdated.setDescription(review.getDescription());
            reviewToBeUpdated.setRating(review.getRating());
            reviewToBeUpdated.setCompanyId(review.getCompanyId());
            return reviewRepository.save(review);
        } else throw new IllegalArgumentException("Review not found");
    }

    @Override
    public Optional<Review> deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            reviewRepository.delete(review);
            return Optional.of(review);
        } else throw new IllegalArgumentException("Review or Company not found");
    }
}
