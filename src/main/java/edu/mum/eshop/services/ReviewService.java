package edu.mum.eshop.services;
import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.review.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReviewService {
    List<Review> getAllNotApprovedReviewRequests();
    Review decideReviewRequest(Review review, Decision decision);
    Optional<Review> getById(Integer id);
    Review save(Review review);
}
