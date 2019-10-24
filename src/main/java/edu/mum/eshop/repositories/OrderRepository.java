package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.order.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderItem, Integer> {

    @Query("select x from OrderItem x where x.product.user.id = :userId")
    public List<OrderItem> getMySales(Integer userId);

    @Query("select x from OrderItem x where x.checkout.id = :checkoutId")
    public List<OrderItem> getOrdersForCheckout(Integer checkoutId);

    @Query("select x from OrderItem x where x.status = 2 and x.product.id = :productId and x.checkout.user.id = :userId")
    public List<OrderItem> getMyDeliveredOrdersByProduct(Integer productId, Integer userId);

}
