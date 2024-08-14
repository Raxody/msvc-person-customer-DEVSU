package com.devsu.msvc_person_customer.service.impl;

import com.devsu.msvc_person_customer.common.exception.BusinessException;
import com.devsu.msvc_person_customer.common.exception.ErrorCodesEnum;
import com.devsu.msvc_person_customer.common.exception.util.ArgumentValidator;
import com.devsu.msvc_person_customer.dto.CustomerDTO;
import com.devsu.msvc_person_customer.entity.Customer;
import com.devsu.msvc_person_customer.entity.Person;
import com.devsu.msvc_person_customer.mapper.CustomerMapper;
import com.devsu.msvc_person_customer.repository.CustomerRepository;
import com.devsu.msvc_person_customer.repository.PersonRepository;
import com.devsu.msvc_person_customer.service.CustomerService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    private final PersonRepository personRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               PersonRepository personRepository) {
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        customerDTO.setStatus(Boolean.TRUE);
        validateCustomerDTO(customerDTO);

        if (customerRepository.findById(customerDTO.getCustomerId()).isPresent()) {
            throw new BusinessException(ErrorCodesEnum.THE_CUSTOMER_IS_ALREADY_REGISTRED, customerDTO.getCustomerId());
        }

        Person person = personRepository.findById(customerDTO.getIdentification())
                .orElseGet(() -> {
                    Person newPerson = customerMapper.toEntity(customerDTO).getPerson();
                    return personRepository.save(newPerson);
                });

        Customer customer = customerMapper.toEntity(customerDTO);
        customer.setPerson(person);
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }


    @Override
    public CustomerDTO getCustomerById(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCodesEnum.THE_CUSTOMER_IS_NOT_FOUND, id));
        return customerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .toList();
    }

    @Override
    public CustomerDTO updateCustomer(String id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCodesEnum.THE_CUSTOMER_IS_NOT_FOUND, id));

        customerDTO.setIdentification(existingCustomer.getPerson().getIdentification());
        customerDTO.setCustomerId(id);
        validateCustomerDTO(customerDTO);
        customerMapper.updateCustomerFromDto(customerDTO, existingCustomer);
        existingCustomer = customerRepository.save(existingCustomer);

        return customerMapper.toDTO(existingCustomer);
    }

    @Override
    public void deleteCustomer(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCodesEnum.THE_CUSTOMER_IS_NOT_FOUND, id));
        customer.setStatus(Boolean.FALSE);
        customerRepository.save(customer);

    }

    private void validateCustomerDTO(CustomerDTO customerDTO) {
        ArgumentValidator.requireNotEmpty(customerDTO.getName(), "Name");
        ArgumentValidator.onlyAcceptLetters(customerDTO.getName(), "Name");

        ArgumentValidator.requireNotEmpty(customerDTO.getGender(), "Gender");
        ArgumentValidator.onlyAcceptLetters(customerDTO.getGender(), "Gender");

        ArgumentValidator.requireNotEmpty(customerDTO.getAge().toString(), "Age");
        ArgumentValidator.requireNumeric(customerDTO.getAge().toString(), "Age");
        ArgumentValidator.requirePositiveAndGreaterThanZero(customerDTO.getAge().toString(), "Age");

        ArgumentValidator.requireNotEmpty(customerDTO.getIdentification(), "Identification");

        ArgumentValidator.requireNotEmpty(customerDTO.getCustomerId(), "Customer ID");

        ArgumentValidator.requireNotEmpty(customerDTO.getAddress(), "Address");

        ArgumentValidator.requireNotEmpty(String.valueOf(customerDTO.getPhone()), "Phone");
        ArgumentValidator.requireNumeric(String.valueOf(customerDTO.getPhone()), "Phone");

        ArgumentValidator.requireNotEmpty(customerDTO.getPassword(), "Password");

        ArgumentValidator.requireNotEmpty(String.valueOf(customerDTO.getStatus()), "Status");
    }
}
