package com.devsu.msvc_person_customer.entity.builder;

import com.devsu.msvc_person_customer.entity.Customer;
import com.devsu.msvc_person_customer.entity.Person;
import com.devsu.msvc_person_customer.util.TestConstants;

public class CustomerTestDataBuilder {
    private String customerId;
    private String password;
    private boolean status;
    private Person person;

    public CustomerTestDataBuilder() {
        this.customerId = TestConstants.DEFAULT_CUSTOMER_ID;
        this.password = TestConstants.DEFAULT_PASSWORD;
        this.status = TestConstants.DEFAULT_STATUS;
        this.person = new Person();
    }

    public CustomerTestDataBuilder withCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public CustomerTestDataBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerTestDataBuilder withStatus(boolean status) {
        this.status = status;
        return this;
    }

    public CustomerTestDataBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setPassword(password);
        customer.setStatus(status);
        customer.setPerson(person);
        return customer;
    }
}
