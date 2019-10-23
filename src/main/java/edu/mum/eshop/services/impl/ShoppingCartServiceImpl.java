package edu.mum.eshop.services.impl;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.purchaseOrder.OrderCheckout;
import edu.mum.eshop.domain.shoppingCart.ShoppingCart;
import edu.mum.eshop.domain.shoppingCart.ShoppingCartItem;
import edu.mum.eshop.repositories.OrderCheckoutRepository;
import edu.mum.eshop.repositories.ShoppingCartRepository;
import edu.mum.eshop.services.ProductService;
import edu.mum.eshop.services.ShoppingCartService;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl extends BaseService implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    OrderCheckoutRepository orderCheckoutRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UsersService usersService;

    @Override
    public ShoppingCart getMyShoppingCart() {
        return shoppingCartRepository.getMyShoppingCart(getUserId());
    }

    @Override
    public ShoppingCart addToCart(Integer productId, Integer quantity) {
        ShoppingCart cart = getMyShoppingCart();
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(usersService.getUserById(getUserId()));
            cart.setItems(new ArrayList<>());
        }

        ShoppingCartItem item = new ShoppingCartItem();
        item.setProduct(productService.getById(productId));
        item.setQuantity(quantity);
        cart.getItems().add(item);
        cart.setTotalSum(cart.getTotalSum() + item.getProduct().getPrice() * item.getQuantity());
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart deleteFromCart(Integer productId) {
        ShoppingCart cart = getMyShoppingCart();
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(usersService.getUserById(getUserId()));
            cart.setItems(new ArrayList<>());
        }

        Optional<ShoppingCartItem> cartItem = cart.getItems().stream().filter(x -> x.getProduct().getId() == productId).findFirst();
        if (cartItem.isPresent()) {
            cart.getItems().remove(cartItem.get());
            cart.setTotalSum(cart.getTotalSum() - cartItem.get().getProduct().getPrice() * cartItem.get().getQuantity());
            cart = shoppingCartRepository.save(cart);
        }

        return cart;
    }

    public void clearShoppingCart() {
        ShoppingCart cart = getMyShoppingCart();
        shoppingCartRepository.delete(cart);
    }

    @Override
    public ZenResult checkout() {
        ShoppingCart cart = getMyShoppingCart();

        OrderCheckout checkout = new OrderCheckout(cart);

        orderCheckoutRepository.save(checkout);

        clearShoppingCart();

        return new ZenResult();
    }
}
