package edu.mum.eshop.services.impl;

import edu.mum.eshop.domain.userinfo.Address;
import edu.mum.eshop.repositories.AddressRepo;
import edu.mum.eshop.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepo addressRepo;

    @Override
    public Address saveAddress(Address address) {
        return addressRepo.save(address);
    }

    @Override
    public Address findShippingAddressByUserId(Integer id) {
        return addressRepo.findShippingAddressByUserId(id);
    }

    @Override
    public Address findBillingAddressByUserId(Integer id) {
        return addressRepo.findBillingAddressByUserId(id);
    }

    @Override
    public Address findAddressById(Integer id) {
        return addressRepo.findAddressById(id);
    }

    @Override
    public void deleteAddressById(Integer id) {
        addressRepo.deleteAddressById(id);
    }
}
