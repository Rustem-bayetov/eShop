package edu.mum.eshop.domain.order;

import edu.mum.eshop.domain.userinfo.Address;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderAddress {
    public OrderAddress(){}

    public OrderAddress(Address address, OrderAddressType addressType){
        this.addressType = addressType;

        if (address == null) return;

        this.city = address.getCity();
        this.street = address.getStreet();
        this.state = address.getState();
        this.zipcode = address.getZipcode();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;

    private String street;

    private String state;

    private String zipcode;

    private OrderAddressType addressType;
}
