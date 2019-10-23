package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.userinfo.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends CrudRepository<Payment, Integer> {
    @Query("select p from Payment p where p.user.id = :id")
    Payment findSPaymentByUserId(Integer id);

    @Query("select p from Payment p where p.id = :id")
    public Payment findPaymentById(Integer id);

    @Query("delete from Payment p where p.id = :id")
    public void deletePaymentById(Integer id);
}
