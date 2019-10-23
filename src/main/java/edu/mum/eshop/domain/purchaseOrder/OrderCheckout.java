package edu.mum.eshop.domain.purchaseOrder;

import edu.mum.eshop.domain.shoppingCart.ShoppingCart;
import edu.mum.eshop.domain.shoppingCart.ShoppingCartItem;
import edu.mum.eshop.domain.users.User;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class OrderCheckout {
    public OrderCheckout() {
    }

    public OrderCheckout(ShoppingCart cart) {
        items = new ArrayList<>();

        if (cart == null) return;

        setUser(cart.getUser());
        for (ShoppingCartItem cartItem : cart.getItems()) {
            items.add(new OrderItem(cartItem));
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
