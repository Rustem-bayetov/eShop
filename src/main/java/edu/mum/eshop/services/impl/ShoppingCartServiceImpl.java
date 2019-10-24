package edu.mum.eshop.services.impl;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.order.*;
import edu.mum.eshop.domain.shoppingCart.ShoppingCart;
import edu.mum.eshop.domain.shoppingCart.ShoppingCartItem;
import edu.mum.eshop.domain.userinfo.Address;
import edu.mum.eshop.domain.userinfo.Payment;
import edu.mum.eshop.domain.users.UserType;
import edu.mum.eshop.repositories.OrderCheckoutRepository;
import edu.mum.eshop.repositories.OrderRepository;
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
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UsersService usersService;

    @Override
    public ShoppingCart getMyShoppingCart() {

        return shoppingCartRepository.getMyShoppingCart(getUserId());
    }

    @Override
    public ZenResult addToCart(Integer productId, Integer quantity) {
        ZenResult result = new ZenResult();
        result.setValue(new ShoppingCart());

        if (!isUserAuthorized()) {
            result.addError("Please log in to add products to shopping cart");
            return result;
        }

        if (!isInRole(UserType.BUYER)) {
            result.addError("Only users with role BUYER can buy products");
            return result;
        }

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
        result.setValue(shoppingCartRepository.save(cart));

        return result;
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
    public ZenResult checkout(Address shippingAddress, Address billingAddress, Payment paymentMethod) {
        if (!isUserAuthorized()) return new ZenResult("Please log in");
        if (!isInRole(UserType.BUYER)) return new ZenResult("Only users with role BUYER can buy products");

        if (shippingAddress == null) return new ZenResult("Please specify shipping address");
        if (billingAddress == null) return new ZenResult("Please specify billing address");
        if (paymentMethod == null) return new ZenResult("Please specify payment method");

        ShoppingCart cart = getMyShoppingCart();
        if (cart == null) return new ZenResult("Please add items to your cart");

        // Decreasing available amount of products
        for (ShoppingCartItem item : cart.getItems()) {
            ZenResult checkAmountResult =  productService.checkDecreaseAvailableProductCount(item.getProduct().getId(), item.getQuantity());
            if (!checkAmountResult.isSuccess()) return checkAmountResult;
        }

        for (ShoppingCartItem item : cart.getItems()) {
            ZenResult decreaseAmountResult = productService.decreaseAvailableProductCount(item.getProduct().getId(), item.getQuantity());
            if (!decreaseAmountResult.isSuccess()) return decreaseAmountResult;
        }

        OrderCheckout checkout = new OrderCheckout(cart);
        checkout.setBillingAddress(new OrderAddress(billingAddress, OrderAddressType.BILLING));
        checkout.setShippingAddress(new OrderAddress(shippingAddress, OrderAddressType.SHIPPING));
        checkout.setPaymentMethod(new OrderPaymentMethod(paymentMethod));

        orderCheckoutRepository.save(checkout);

        clearShoppingCart();

        return new ZenResult();
    }
}
