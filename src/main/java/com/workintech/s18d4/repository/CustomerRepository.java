package com.workintech.s18d4.repository;

import com.workintech.s18d4.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
