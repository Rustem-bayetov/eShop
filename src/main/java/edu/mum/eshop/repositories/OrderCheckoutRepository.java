package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.order.OrderCheckout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderCheckoutRepository extends CrudRepository<OrderCheckout, Integer> {

    @Query("select x from OrderCheckout x where x.user.id = :userId")
    public List<OrderCheckout> getMyOrders(Integer userId);
}
