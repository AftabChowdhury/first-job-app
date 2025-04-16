package com.ac.firstjobapp.review.implementation;

import com.ac.firstjobapp.company.Company;
import com.ac.firstjobapp.company.CompanyService;
import com.ac.firstjobapp.review.Review;
import com.ac.firstjobapp.review.ReviewRepository;
import com.ac.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.getCompany(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Long companyId, Review updatedReview) {
        if (companyService.getCompany(companyId) != null) {
            updatedReview.setCompany(companyService.getCompany(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReview2(Long reviewId, Long companyId, Review updatedReview) {
        if (companyService.getCompany(companyId) != null) {
            Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
            if (reviewOptional.isPresent()) {
                Review review = reviewOptional.get();
                review.setTitle(updatedReview.getTitle());
                review.setDescription(updatedReview.getDescription());
                review.setRating(updatedReview.getRating());
                reviewRepository.save(review);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId, Long companyId) {
        try {
            if (companyService.getCompany(companyId) != null && reviewRepository.existsById(reviewId)) {
                reviewRepository.deleteById(reviewId);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
