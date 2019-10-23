package edu.mum.eshop.services;

import edu.mum.eshop.domain.userinfo.Address;

public interface AddressService {
    public Address saveAddress(Address address);
    public Address findShippingAddressByUserId(Integer id);
    public Address findBillingAddressByUserId(Integer id);
}
