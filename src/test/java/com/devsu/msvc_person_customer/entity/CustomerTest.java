package com.devsu.msvc_person_customer.entity;

import com.devsu.msvc_person_customer.util.TestConstants;
import com.devsu.msvc_person_customer.entity.builder.CustomerTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    private CustomerTestDataBuilder customerBuilder;

    @BeforeEach
    public void setUp() {
        customerBuilder = new CustomerTestDataBuilder();
    }

    @Test
    public void testCustomerIdSetterGetter() {
        // Arrange
        Customer customer = customerBuilder.withCustomerId(TestConstants.UPDATED_CUSTOMER_ID).build();

        // Act
        String result = customer.getCustomerId();

        // Assert
        assertEquals(TestConstants.UPDATED_CUSTOMER_ID, result);
    }

    @Test
    public void testPasswordSetterGetter() {
        // Arrange
        Customer customer = customerBuilder.withPassword(TestConstants.UPDATED_PASSWORD).build();

        // Act
        String result = customer.getPassword();

        // Assert
        assertEquals(TestConstants.UPDATED_PASSWORD, result);
    }

    @Test
    public void testStatusSetterGetter() {
        // Arrange
        Customer customer = customerBuilder.withStatus(TestConstants.UPDATED_STATUS).build();

        // Act
        boolean result = customer.getStatus();

        // Assert
        assertEquals(TestConstants.UPDATED_STATUS, result);
    }

    @Test
    public void testPersonAssociation() {
        // Arrange
        Person person = new Person();
        person.setName(TestConstants.PERSON_NAME);
        person.setIdentification(TestConstants.PERSON_IDENTIFICATION);

        Customer customer = customerBuilder.withPerson(person).build();

        // Act
        Person associatedPerson = customer.getPerson();

        // Assert
        assertEquals(TestConstants.PERSON_NAME, associatedPerson.getName());
        assertEquals(TestConstants.PERSON_IDENTIFICATION, associatedPerson.getIdentification());
    }
}
