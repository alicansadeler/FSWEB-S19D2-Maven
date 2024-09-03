package com.workintech.s18d4.repository;

import com.workintech.s18d4.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
