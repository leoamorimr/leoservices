package com.leoamorimr.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public  Customer findCustomerByEmail(String email);
}
