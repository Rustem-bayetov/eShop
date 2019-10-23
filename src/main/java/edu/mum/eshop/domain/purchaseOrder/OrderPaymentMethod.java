package edu.mum.eshop.domain.purchaseOrder;

import edu.mum.eshop.domain.userinfo.Payment;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderPaymentMethod {
    public OrderPaymentMethod(){}

    public OrderPaymentMethod(Payment paymentMethod){
        if (paymentMethod == null) return;
        this.nameOnCard = paymentMethod.getCardName();
        this.cardNumber = paymentMethod.getCardNumber();
        this.expiration = paymentMethod.getExpiration();
        this.cvv = paymentMethod.getCvv();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameOnCard;

    private String cardNumber;

    private String expiration;

    private String cvv;
}
