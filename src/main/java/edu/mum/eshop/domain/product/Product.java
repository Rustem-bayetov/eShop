package edu.mum.eshop.domain.product;

import edu.mum.eshop.domain.product.Category;
import edu.mum.eshop.domain.users.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 4, max = 255)
    private String title;

    @NotEmpty
    private String description;

    @Min(0)
    @NotNull
    private Double price;

    @Max(100)
    @NotNull
    private Integer discount;

    @NotEmpty
    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Min(0)
    @NotNull
    @Column(name = "AVAILABLE_COUNT")
    private Integer availableCount;

    @NotNull
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public Product(){
        id = 0;
        status = ProductStatus.ACTIVE;
    }
}
