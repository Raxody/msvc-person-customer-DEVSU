package com.devsu.msvc_person_customer.integration;

import com.devsu.msvc_person_customer.dto.AccountDTO;
import com.devsu.msvc_person_customer.dto.CustomerDTO;
import com.devsu.msvc_person_customer.dto.CustomerWithAccountDTO;
import com.devsu.msvc_person_customer.entity.builder.AccountDTOTestDataBuilder;
import com.devsu.msvc_person_customer.entity.builder.CustomerDTOTestDataBuilder;
import com.devsu.msvc_person_customer.entity.builder.CustomerWithAccountDTOTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createCustomerWithDefaultAccountTest() throws Exception {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTOTestDataBuilder()
                .withCustomerId("1234567890")
                .withName("Test Name")
                .withGender("Male")
                .withAge(30)
                .withAddress("Test Address")
                .withPhone(123456789L)
                .withPassword("TestPass123")
                .withIdentification("999999999")
                .build();

        AccountDTO accountDTO = new AccountDTOTestDataBuilder()
                .withCustomerId(customerDTO.getCustomerId())
                .withAccountType("Ahorros")
                .withInitialBalance(55000.25)
                .build();

        CustomerWithAccountDTO customerWithAccountDTO = new CustomerWithAccountDTOTestDataBuilder()
                .withCustomerDTO(customerDTO)
                .withAccountDTO(accountDTO)
                .build();

        // Act
        mockMvc.perform(post("/customers/withDefaultAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerWithAccountDTO)))
                .andExpect(status().isCreated());

        // Assert
        MvcResult result = mockMvc.perform(get("/customers/{id}", "1234567890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        CustomerDTO retrievedCustomer = asObject(result.getResponse().getContentAsString(), CustomerDTO.class);
        assertThat(retrievedCustomer).isNotNull();
        assertThat(retrievedCustomer.getCustomerId()).isEqualTo("1234567890");
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T asObject(String json, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
