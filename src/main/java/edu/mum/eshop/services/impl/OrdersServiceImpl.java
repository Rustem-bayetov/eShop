package edu.mum.eshop.services.impl;

import edu.mum.eshop.domain.order.OrderCheckout;
import edu.mum.eshop.repositories.OrderCheckoutRepository;
import edu.mum.eshop.services.OrdersService;
import edu.mum.eshop.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl extends BaseService implements OrdersService {

    @Autowired
    OrderCheckoutRepository orderCheckoutRepository;

    @Override
    public List<OrderCheckout> getMyCheckouts(){
        return Util.iterableToCollection(orderCheckoutRepository.getMyOrders(getUserId()));
    }

    @Override
    public OrderCheckout getMyCheckoutById(Integer id) {
        return orderCheckoutRepository.findById(id).orElse(null);
    }
}
