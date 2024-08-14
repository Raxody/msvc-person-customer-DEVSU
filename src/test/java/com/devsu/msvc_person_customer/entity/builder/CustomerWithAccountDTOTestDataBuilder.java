package com.devsu.msvc_person_customer.entity.builder;

import com.devsu.msvc_person_customer.dto.AccountDTO;
import com.devsu.msvc_person_customer.dto.CustomerDTO;
import com.devsu.msvc_person_customer.dto.CustomerWithAccountDTO;

public class CustomerWithAccountDTOTestDataBuilder {
    private CustomerDTO customerDTO;
    private AccountDTO accountDTO;

    public CustomerWithAccountDTOTestDataBuilder withCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
        return this;
    }

    public CustomerWithAccountDTOTestDataBuilder withAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
        return this;
    }

    public CustomerWithAccountDTO build() {
        CustomerWithAccountDTO customerWithAccountDTO = new CustomerWithAccountDTO();
        customerWithAccountDTO.setCustomerDTO(this.customerDTO);
        customerWithAccountDTO.setAccountDTO(this.accountDTO);
        return customerWithAccountDTO;
    }
}