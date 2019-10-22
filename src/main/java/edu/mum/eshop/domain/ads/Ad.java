package edu.mum.eshop.domain.ads;

import edu.mum.eshop.domain.product.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data @Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @NotNull
    private Product product;
    @OneToOne
    @NotNull
    private AdRequest adRequest;

}
