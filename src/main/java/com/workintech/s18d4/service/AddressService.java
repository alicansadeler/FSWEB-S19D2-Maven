package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Address;

import java.util.List;

public interface AddressService {

    Address save(Address address);
    Address delete(Long id);

    List<Address> findAll();
    Address find(Long id);

}
