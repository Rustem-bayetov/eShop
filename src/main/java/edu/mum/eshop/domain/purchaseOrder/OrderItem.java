package edu.mum.eshop.domain.purchaseOrder;

import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.domain.shoppingCart.ShoppingCartItem;
import lombok.Data;

import javax.persistence.*;

@Data
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

    @ManyToOne
    @JoinColumn(name = "checkout_id")
    private OrderCheckout checkout;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private OrderStatus status;


}
