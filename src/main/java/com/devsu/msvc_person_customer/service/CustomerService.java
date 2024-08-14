package com.devsu.msvc_person_customer.service;

import com.devsu.msvc_person_customer.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(String id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(String id, CustomerDTO customerDTO);

    void deleteCustomer(String id);
}
