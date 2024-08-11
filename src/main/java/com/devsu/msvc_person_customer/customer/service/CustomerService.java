package com.devsu.msvc_person_customer.customer.service;

import com.devsu.msvc_person_customer.customer.dto.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(String id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(String id, CustomerDTO customerDTO);

    void deleteCustomer(String id);
}
