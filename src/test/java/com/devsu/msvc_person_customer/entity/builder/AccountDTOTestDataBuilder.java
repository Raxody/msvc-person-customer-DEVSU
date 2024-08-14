package com.devsu.msvc_person_customer.entity.builder;

import com.devsu.msvc_person_customer.dto.AccountDTO;

public class AccountDTOTestDataBuilder {

    private String accountType;
    private Double initialBalance;
    private String customerId;

    public AccountDTOTestDataBuilder withAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public AccountDTOTestDataBuilder withInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
        return this;
    }

    public AccountDTOTestDataBuilder withCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public AccountDTO build() {
        return new AccountDTO(accountType, initialBalance, customerId);
    }
}