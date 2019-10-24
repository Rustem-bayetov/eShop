package edu.mum.eshop.services;

import edu.mum.eshop.domain.order.OrderCheckout;

import java.util.List;

public interface OrdersService {
    List<OrderCheckout> getMyCheckouts();

    OrderCheckout getMyCheckoutById(Integer id);
}
