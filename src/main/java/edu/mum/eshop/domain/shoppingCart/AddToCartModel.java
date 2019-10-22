package edu.mum.eshop.domain.shoppingCart;

import lombok.Data;

@Data
public class AddToCartModel {
    private Integer productId;
    private Integer quantity;
}
