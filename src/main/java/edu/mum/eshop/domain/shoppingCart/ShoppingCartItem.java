package edu.mum.eshop.domain.shoppingCart;

import edu.mum.eshop.domain.product.Product;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
}
