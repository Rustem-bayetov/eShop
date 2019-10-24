package edu.mum.eshop.domain.order;

import edu.mum.eshop.domain.shoppingCart.ShoppingCart;
import edu.mum.eshop.domain.shoppingCart.ShoppingCartItem;
import edu.mum.eshop.domain.users.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class OrderCheckout {
    public OrderCheckout() {
    }

    public OrderCheckout(ShoppingCart cart) {
        items = new ArrayList<>();

        if (cart == null) return;

        checkoutDate = new Date();
        totalSum = cart.getTotalSum();

        setUser(cart.getUser());
        for (ShoppingCartItem cartItem : cart.getItems()) {
            items.add(new OrderItem(cartItem));
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date checkoutDate;

    private double totalSum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "checkout_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name = "shipping_address")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private OrderAddress shippingAddress;

    @ManyToOne
    @JoinColumn(name = "billing_address")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private OrderAddress billingAddress;

    @OneToOne
    @JoinColumn(name = "payment_method")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private OrderPaymentMethod paymentMethod;
}
