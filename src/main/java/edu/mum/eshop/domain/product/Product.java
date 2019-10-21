package edu.mum.eshop.domain.product;

import edu.mum.eshop.domain.product.Category;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    private Double price;

    private Integer discount;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "AVAILABLE_COUNT")
    private Integer availableCount;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
