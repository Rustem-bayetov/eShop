package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.ads.AdRequest;
import edu.mum.eshop.domain.review.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
    @Query(value = "SELECT * FROM review WHERE review_status = 0", nativeQuery = true)
    List<Review> findUnApprovedReviewRequests();

    @Query("select x from Review x where x.reviewStatus = 1 and x.product.id = :productId")
    List<Review> findAllByProductId(Integer productId);
}
