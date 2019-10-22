package edu.mum.eshop.services.impl;

import edu.mum.eshop.Session;
import edu.mum.eshop.domain.shoppingCart.ShoppingCart;
import edu.mum.eshop.domain.shoppingCart.ShoppingCartItem;
import edu.mum.eshop.repositories.ShoppingCartRepository;
import edu.mum.eshop.services.ProductService;
import edu.mum.eshop.services.ShoppingCartService;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UsersService usersService;

    @Override
    public ShoppingCart getMyShoppingCart() {
        return shoppingCartRepository.getMyShoppingCart(Session.getMyId());
    }

    @Override
    public ShoppingCart addToCart(Integer productId, Integer quantity) {
        ShoppingCart cart = getMyShoppingCart();
        if (cart == null){
            cart = new ShoppingCart();
            cart.setUser(usersService.getUserById(Session.getMyId()));
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
            cart.setUser(usersService.getUserById(Session.getMyId()));
            cart.setItems(new ArrayList<>());
        }

        Optional<ShoppingCartItem> cartItem = cart.getItems().stream().filter(x->x.getProduct().getId() == productId).findFirst();
        if (cartItem.isPresent()){
            cart.getItems().remove(cartItem.get());
            cart.setTotalSum(cart.getTotalSum() - cartItem.get().getProduct().getPrice() * cartItem.get().getQuantity());
            cart = shoppingCartRepository.save(cart);
        }

        return cart;
    }
}