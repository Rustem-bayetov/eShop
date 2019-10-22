package edu.mum.eshop.domain.purchaseOrder;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;

    private String street;

    private String state;

    private String zipcode;

    private OrderAddressType addressType;
}
