package edu.mum.eshop.services.impl;

import edu.mum.eshop.domain.userinfo.Payment;
import edu.mum.eshop.repositories.PaymentRepo;
import edu.mum.eshop.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepo paymentRepo;

    @Override
    public Payment savePaymentInfo(Payment payment) {
        return paymentRepo.save(payment);
    }
}
