package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.userinfo.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address, Integer> {

    @Query("select a from Address a where a.user.id = :id and a.shipping = true")
    Address findShippingAddressByUserId(Integer id);

    @Query("select a from Address a where a.user.id = :id and a.billing = true")
    Address findBillingAddressByUserId(Integer id);
}
