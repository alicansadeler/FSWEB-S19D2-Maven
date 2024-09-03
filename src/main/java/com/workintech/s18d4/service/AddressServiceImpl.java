package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Address;

import com.workintech.s18d4.repository.AddressRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;


    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;

    }


    @Override
    @Transactional
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address delete(Long id) {
        Address address = addressRepository.findById(id).get();
        addressRepository.delete(address);
        return address;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address find(Long id) {
        return addressRepository.findById(id).get();
    }




}
