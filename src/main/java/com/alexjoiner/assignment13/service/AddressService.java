package com.alexjoiner.assignment13.service;

import com.alexjoiner.assignment13.domain.Address;
import com.alexjoiner.assignment13.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    public AddressRepository addressRepository;

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

}
