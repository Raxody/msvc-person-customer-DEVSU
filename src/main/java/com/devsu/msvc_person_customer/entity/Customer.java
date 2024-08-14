package com.devsu.msvc_person_customer.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id", nullable = false, unique = true)
    private String customerId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "identification", nullable = false)
    private Person person;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean status;

    // Getters y Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
