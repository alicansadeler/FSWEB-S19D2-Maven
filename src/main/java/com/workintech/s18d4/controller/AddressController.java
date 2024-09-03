package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getAddresses() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable("id") Long id) {
        return  addressService.find(id);
    }

    @PostMapping
    public Address saveAddress(@RequestBody Address address) {
        return addressService.save(address);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable("id") Long id) {
        Address address = addressService.find(id);
        return addressService.save(address);
    }

    @DeleteMapping("/{id}")
    public Address deleteAddress(@PathVariable("id") Long id) {
        return addressService.delete(id);
    }
    
}
