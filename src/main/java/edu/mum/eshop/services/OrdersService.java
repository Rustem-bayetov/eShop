package edu.mum.eshop.services;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.order.OrderCheckout;
import edu.mum.eshop.domain.order.OrderItem;

import java.util.List;

public interface OrdersService {
    List<OrderCheckout> getMyCheckouts();

    OrderCheckout getMyCheckoutById(Integer id);

    List<OrderItem> getMySales();

    ZenResult shipOrder(Integer orderId);

    ZenResult orderDelivered(Integer orderId);

    ZenResult cancelOrder(Integer orderId);

    ZenResult returnOrder(Integer orderId);
}
