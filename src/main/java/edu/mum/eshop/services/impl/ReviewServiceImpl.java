package edu.mum.eshop.services.impl;

import edu.mum.eshop.domain.ads.Ad;
import edu.mum.eshop.domain.ads.AdRequest;
import edu.mum.eshop.domain.ads.AdRequestStatus;
import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.review.Review;
import edu.mum.eshop.domain.review.ReviewStatus;
import edu.mum.eshop.repositories.ReviewRepository;
import edu.mum.eshop.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
@Autowired ReviewRepository reviewRepository;

    @Override public List<Review> getAllNotApprovedReviewRequests(){
        return reviewRepository.findUnApprovedReviewRequests();
    }
    @Override
    public Review decideReviewRequest(Review review, Decision decision) {
        if (decision == Decision.APPROVE) review.setReviewStatus(ReviewStatus.APPROVED);
        else if (decision == Decision.REJECT) review.setReviewStatus(ReviewStatus.REJECTED);
        Review savedReview = reviewRepository.save(review);
        return savedReview;
    }

    @Override
    public Optional<Review> getById(Integer id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }
}
