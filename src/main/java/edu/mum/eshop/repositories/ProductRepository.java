package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query("select x from Product x where x.status = 0")
    List<Product> getAllProducts();

    @Query("select x from Product x where x.user.id = :userId")
    List<Product> getMyProducts(Integer userId);

}
