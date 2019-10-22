package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.purchaseOrder.OrderCheckout;
import org.springframework.data.repository.CrudRepository;

public interface OrderCheckoutRepository extends CrudRepository<OrderCheckout, Integer> {
}
