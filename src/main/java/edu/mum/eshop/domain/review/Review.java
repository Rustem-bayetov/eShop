package edu.mum.eshop.domain.review;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.domain.users.User;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity @Data
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty private String body;
    @NotNull private ReviewStatus reviewStatus = ReviewStatus.CREATED;
    @ManyToOne private Product product;
    @ManyToOne @JoinColumn(name = "user_id") private User user;
}