package com.devsu.msvc_person_customer.repository;

import com.devsu.msvc_person_customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    List<Customer> findByPersonIdentification(String identification);

}
