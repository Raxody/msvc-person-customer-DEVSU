package com.devsu.msvc_person_customer.entity.builder;

import com.devsu.msvc_person_customer.dto.CustomerDTO;

public class CustomerDTOTestDataBuilder {
    private String customerId;
    private String name ;
    private String gender;
    private int age ;
    private String address;
    private long phone ;
    private String password;
    private String identification;

    public CustomerDTOTestDataBuilder withCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public CustomerDTOTestDataBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerDTOTestDataBuilder withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public CustomerDTOTestDataBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public CustomerDTOTestDataBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public CustomerDTOTestDataBuilder withPhone(long phone) {
        this.phone = phone;
        return this;
    }

    public CustomerDTOTestDataBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerDTOTestDataBuilder withIdentification(String identification) {
        this.identification = identification;
        return this;
    }

    public CustomerDTO build() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(this.customerId);
        customerDTO.setName(this.name);
        customerDTO.setGender(this.gender);
        customerDTO.setAge(this.age);
        customerDTO.setAddress(this.address);
        customerDTO.setPhone(this.phone);
        customerDTO.setPassword(this.password);
        customerDTO.setIdentification(this.identification);
        return customerDTO;
    }
}