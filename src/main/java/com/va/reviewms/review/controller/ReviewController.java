package com.va.reviewms.review.controller;

import com.va.reviewms.review.model.Review;
import com.va.reviewms.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviews(@RequestParam Long companyId) {
        List<Review> reviews = reviewService.findAll(companyId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        Review review = reviewService.findById(reviewId);
        return ResponseEntity.ok(review);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        Review createdReview = reviewService.createReview(companyId, review);
        return ResponseEntity.ok(createdReview);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        Review reviewUpdated = reviewService.updateReview(reviewId, review);
        return ResponseEntity.ok(reviewUpdated);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long reviewId) {
        Optional<Review> reviewDeleted = reviewService.deleteReview(reviewId);
        return reviewDeleted.map(ResponseEntity::ok).orElseThrow(() -> new IllegalArgumentException("Review not found"));
    }
}
