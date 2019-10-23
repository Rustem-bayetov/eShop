package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.userinfo.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends CrudRepository<Payment, Integer> {
}
