package edu.mum.eshop.controllers;
import edu.mum.eshop.domain.ads.AdRequest;
import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.domain.review.Review;
import edu.mum.eshop.domain.review.ReviewStatus;
import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.services.AdService;
import edu.mum.eshop.services.ProductService;
import edu.mum.eshop.services.ReviewService;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Optional;

@SessionAttributes("loggedInUser")
@RequestMapping("/review")
@Controller
public class ReviewController {
    @Autowired AdService adService;
    @Autowired ProductService productService;
    @Autowired UsersService usersService;
    @Autowired ReviewService reviewService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("getall")
    public String get(Model model) {
        List<Review> reviews = reviewService.getAllNotApprovedReviewRequests();
        model.addAttribute("reviews", reviews);
        return "review/reviewList";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{reviewid}/{decision}")
    public String approveReview(@PathVariable("reviewid") Integer id, @PathVariable("decision") Decision decision, Model model) {
        Optional<Review> review = reviewService.getById(id);
        Product prod = productService.getById(review.get().getProduct().getId());
        User user = usersService.getUserById(review.get().getUser().getId());
        model.addAttribute("review", review.get());
        model.addAttribute("prod", prod);
        model.addAttribute("user", user);
        if (decision == Decision.APPROVE) {
            System.out.println("Approved");
            reviewService.decideReviewRequest(review.get(), Decision.APPROVE);
        } else if (decision == Decision.REJECT) {
            System.out.println("Rejected");
            reviewService.decideReviewRequest(review.get(), Decision.REJECT); }
        return "/review/getall";
    }

    @GetMapping("createReviews")
    public String createRevs(){
        Product p1 = productService.getById(1);
        Product p2 = productService.getById(2);
        Product p3 = productService.getById(3);
        System.out.println("got prods" + p1+ "\n" +p2 + "\n"+p3);
        User u1 = usersService.getUserById(1);
        User u2 = usersService.getUserById(2);
        User u3 = usersService.getUserById(8);
        System.out.println("got users" + u1+ "\n" +u2 + "\n"+u3);
        Review r1 = new Review();
        Review r2 = new Review();
        Review r3 = new Review();
        System.out.println("created empty revs");
        r1.setReviewStatus(ReviewStatus.CREATED);
        r2.setReviewStatus(ReviewStatus.CREATED);
        r3.setReviewStatus(ReviewStatus.CREATED);
        System.out.println("set revs status");
        r1.setUser(u1);
        r2.setUser(u2);
        r3.setUser(u3);
        System.out.println("set revs users");
        r1.setBody("Review One Body");
        r2.setBody("Review Two Body");
        r3.setBody("Review Three Body");
        System.out.println("set revs bodies");
        r1.setProduct(p1);
        r2.setProduct(p2);
        r3.setProduct(p3);
        System.out.println("set revs prods");
        System.out.println("revs are" + r1+ "\n" +r2 + "\n"+r3);
        reviewService.save(r1);
        reviewService.save(r2);
        reviewService.save(r3);
        return "/review/reviewList";
    }
}
