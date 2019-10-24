package edu.mum.eshop.services.impl;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.order.OrderItem;
import edu.mum.eshop.domain.review.Review;
import edu.mum.eshop.domain.review.ReviewCreateModel;
import edu.mum.eshop.domain.review.ReviewStatus;
import edu.mum.eshop.repositories.OrderRepository;
import edu.mum.eshop.repositories.ReviewRepository;
import edu.mum.eshop.services.ProductService;
import edu.mum.eshop.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl extends BaseService implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @Override
    public List<Review> getAllNotApprovedReviewRequests() {
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

    @Override
    public ZenResult addReview(ReviewCreateModel model) {
        List<OrderItem> orders = orderRepository.getMyDeliveredOrdersByProduct(model.productId, getUserId());

        if (orders == null || orders.size() == 0) return new ZenResult("Sorry but you can't comment this product. Product should be bought and delivered.");

        Review review = new Review();
        review.setReviewDate(new Date());
        review.setUser(getUser());
        review.setBody(model.body);
        review.setProduct(productService.getById(model.getProductId()));
        review.setReviewStatus(ReviewStatus.CREATED);

        reviewRepository.save(review);

        return new ZenResult();
    }

    @Override
    public List<Review> getProductReviews(Integer productId){
        return reviewRepository.findAllByProductId(productId);
    }
}
