package edu.mum.eshop.services.impl;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.order.OrderCheckout;
import edu.mum.eshop.domain.order.OrderItem;
import edu.mum.eshop.domain.order.OrderStatus;
import edu.mum.eshop.repositories.OrderCheckoutRepository;
import edu.mum.eshop.repositories.OrderRepository;
import edu.mum.eshop.services.OrdersService;
import edu.mum.eshop.services.UsersService;
import edu.mum.eshop.util.Util;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl extends BaseService implements OrdersService {

    @Autowired
    OrderCheckoutRepository orderCheckoutRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UsersService usersService;

    @Override
    public List<OrderCheckout> getMyCheckouts() {
        return Util.iterableToCollection(orderCheckoutRepository.getMyOrders(getUserId()));
    }

    @Override
    public OrderCheckout getMyCheckoutById(Integer id) {
        OrderCheckout checkout =  orderCheckoutRepository.findById(id).orElse(null);
        if (checkout == null) return null;

        if (checkout.getUser().getId() != getUserId()) return null;

        return checkout;
    }

    @Override
    public List<OrderItem> getMySales() {
        return Util.iterableToCollection(orderRepository.getMySales(getUserId()));
    }

    @Override
    public ZenResult shipOrder(Integer orderId) {

        Optional<OrderItem> orderItemOptional = orderRepository.findById(orderId);
        if (!orderItemOptional.isPresent()) return new ZenResult("Can't find order with ID = " + orderId);

        OrderItem orderItem = orderItemOptional.get();
        if (orderItem.getStatus() != OrderStatus.PLACED) return new ZenResult("Can't mark this order as shiiped. Order status = " + orderItem.getStatus().name());
        if (orderItem.getProduct().getUser().getId() != getUserId()) return new ZenResult("You can't manage this order");

        orderItem.setStatus(OrderStatus.SHIPPED);
        ZenResult result = new ZenResult();

        orderItem = orderRepository.save(orderItem);

        result.setValue(orderItem.getStatus());

        return result;
    }

    @Override
    public ZenResult cancelOrder(Integer orderId) {
        Optional<OrderItem> orderItemOptional = orderRepository.findById(orderId);
        if (!orderItemOptional.isPresent()) return new ZenResult("Can't find order with ID = " + orderId);

        OrderItem orderItem = orderItemOptional.get();
        if (orderItem.getStatus() != OrderStatus.PLACED) return new ZenResult("Can't cancel this order. Order status = " + orderItem.getStatus().name());
        if (orderItem.getCheckout().getUser().getId() != getUserId()) return new ZenResult("You can't manage this order");

        orderItem.setStatus(OrderStatus.CANCELLED);

        ZenResult result = new ZenResult();
        orderItem = orderRepository.save(orderItem);
        result.setValue(orderItem.getStatus());

        // usersService.useLoyaltyPoints(Math.round((float) (orderItem.getQuantity() * orderItem.getProduct().getPrice()) / 100));

        return result;
    }

    @Override
    public ZenResult orderDelivered(Integer orderId) {
        Optional<OrderItem> orderItemOptional = orderRepository.findById(orderId);
        if (!orderItemOptional.isPresent()) return new ZenResult("Can't find order with ID = " + orderId);

        OrderItem orderItem = orderItemOptional.get();
        if (orderItem.getStatus() != OrderStatus.SHIPPED) return new ZenResult("Can't mark this order as delivered. Order status = " + orderItem.getStatus().name());
        if (orderItem.getProduct().getUser().getId() != getUserId()) return new ZenResult("You can't manage this order");

        orderItem.setStatus(OrderStatus.DELIVERED);
        orderItem.setDeliveredDate(new Date());

        ZenResult result = new ZenResult();
        orderItem = orderRepository.save(orderItem);
        result.setValue(orderItem.getStatus());

        return result;
    }

    @Override
    public ZenResult returnOrder(Integer orderId) {
        Optional<OrderItem> orderItemOptional = orderRepository.findById(orderId);
        if (!orderItemOptional.isPresent()) return new ZenResult("Can't find order with ID = " + orderId);

        OrderItem orderItem = orderItemOptional.get();
        if (orderItem.getStatus() != OrderStatus.DELIVERED) return new ZenResult("Can't return this order. Order status = " + orderItem.getStatus().name());
        if (orderItem.getCheckout().getUser().getId() != getUserId()) return new ZenResult("You can't manage this order");

        orderItem.setStatus(OrderStatus.RETURNED);

        ZenResult result = new ZenResult();
        orderItem = orderRepository.save(orderItem);
        result.setValue(orderItem.getStatus());

        // usersService.useLoyaltyPoints(Math.round((float) (orderItem.getQuantity() * orderItem.getProduct().getPrice()) / 100));

        return result;
    }

}
