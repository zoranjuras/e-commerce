package com.cmcommerce.ecommerce.dao;

import com.cmcommerce.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
