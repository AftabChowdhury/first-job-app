package com.ac.firstjobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews(Long companyId);
    boolean createReview(Long companyId, Review review);
    Review getReview(Long companyId, Long reviewId);
    boolean updateReview(Long reviewId, Long companyId, Review review);
    boolean updateReview2(Long reviewId, Long companyId, Review review);
    boolean deleteReview(Long reviewId, Long companyId);
}
