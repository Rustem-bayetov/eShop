package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.shoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {

    @Query("select x from ShoppingCart x where x.user.id = :userId")
    ShoppingCart getMyShoppingCart(Integer userId);
}
