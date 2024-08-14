package com.devsu.msvc_person_customer.dto;

public class AccountDTO {

    private String accountType;
    private Double initialBalance;
    private String customerId;

    public AccountDTO() {
    }

    public AccountDTO(String accountType, Double initialBalance, String customerId) {
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
