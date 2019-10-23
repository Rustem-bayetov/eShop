package edu.mum.eshop.services;

import edu.mum.eshop.domain.userinfo.Payment;

public interface PaymentService {
    public Payment savePaymentInfo(Payment payment);

    public Payment findSPaymentByUserId(Integer id);

    public Payment findPaymentById(Integer id);

    public void deletePaymentById(Integer id);
}
