package edu.mum.eshop.services;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.shoppingCart.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart getMyShoppingCart();

    ShoppingCart addToCart(Integer productId, Integer quantity);

    ShoppingCart deleteFromCart(Integer productId);

    ZenResult checkout();
}
