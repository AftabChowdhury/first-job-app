package com.ac.firstjobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/company/{companyId}/reviews")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/company/{companyId}/review/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);
    }



    @PostMapping("/company/{companyId}/review")
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isReviewCreated = reviewService.createReview(companyId, review);
        if (isReviewCreated) {
            return new ResponseEntity<>("Review Successfully Created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/company/{companyId}/review/{reviewId}")
    public ResponseEntity<String> updateReview2(@PathVariable Long reviewId, @PathVariable Long companyId, @RequestBody Review review) {
        boolean isReviewUpdated = reviewService.updateReview(reviewId, companyId, review);
        if(isReviewUpdated) {
            return new ResponseEntity<>("Review updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not Updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/company/{companyId}/review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId, @PathVariable Long companyId) {
        boolean isDeleted = reviewService.deleteReview(reviewId, companyId);
        if (isDeleted) return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
