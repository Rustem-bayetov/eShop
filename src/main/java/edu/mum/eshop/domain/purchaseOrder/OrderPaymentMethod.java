package edu.mum.eshop.domain.purchaseOrder;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderPaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameOnCard;

    private String cardNumber;

    private String expiration;

    private String cvv;
}
