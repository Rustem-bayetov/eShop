package edu.mum.eshop.services;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.shoppingCart.ShoppingCart;
import edu.mum.eshop.domain.userinfo.Address;
import edu.mum.eshop.domain.userinfo.Payment;

public interface ShoppingCartService {

    ShoppingCart getMyShoppingCart();

    ZenResult addToCart(Integer productId, Integer quantity);

    ShoppingCart deleteFromCart(Integer productId);

    ZenResult checkout(Address shippingAddress, Address billingAddress, Payment paymentMethod, boolean useLoyaltyPoints);
}
