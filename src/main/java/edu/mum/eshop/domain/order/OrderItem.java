package edu.mum.eshop.domain.order;

import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.domain.shoppingCart.ShoppingCartItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class OrderItem {
    public OrderItem(){}

    public OrderItem(ShoppingCartItem cartItem){
        product = cartItem.getProduct();
        quantity = cartItem.getQuantity();
        status = OrderStatus.PLACED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "checkout_id")
    private OrderCheckout checkout;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private OrderStatus status;

    private Date deliveredDate;
}
