package com.devsu.msvc_person_customer.controller;

import com.devsu.msvc_person_customer.client.AsyncCustomerAccountClient;
import com.devsu.msvc_person_customer.dto.CustomerDTO;
import com.devsu.msvc_person_customer.dto.CustomerWithAccountDTO;
import com.devsu.msvc_person_customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@RestController
@RequestMapping("/customers")

public class CustomerController {
    private final CustomerService customerService;
    private final AsyncCustomerAccountClient asyncCustomerAccountClient;
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService, AsyncCustomerAccountClient asyncCustomerAccountClient) {
        this.customerService = customerService;
        this.asyncCustomerAccountClient = asyncCustomerAccountClient;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/withDefaultAccount")
    public ResponseEntity<CustomerDTO> createCustomerWithDefaultAccount(@RequestBody CustomerWithAccountDTO customerWithAccountDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerWithAccountDTO.getCustomerDTO());
        customerWithAccountDTO.getAccountDTO().setCustomerId(customerWithAccountDTO.getCustomerDTO().getCustomerId());
        asyncCustomerAccountClient.createDefaultAccountAsync(customerWithAccountDTO.getAccountDTO())
                .exceptionally(ex -> {
                    log.error("Error creando la cuenta por default para el cliente: {}", customerWithAccountDTO.getAccountDTO().getCustomerId(), ex);
                    return null;
                });
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Customer deleted", HttpStatus.OK);
    }
}
