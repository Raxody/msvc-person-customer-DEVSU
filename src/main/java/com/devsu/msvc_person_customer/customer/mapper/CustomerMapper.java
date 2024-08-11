package com.devsu.msvc_person_customer.customer.mapper;

import com.devsu.msvc_person_customer.customer.dto.CustomerDTO;
import com.devsu.msvc_person_customer.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface CustomerMapper {
    @Mapping(target = "person.identification", source = "identification")
    @Mapping(target = "person.name", source = "name")
    @Mapping(target = "person.gender", source = "gender")
    @Mapping(target = "person.age", source = "age")
    @Mapping(target = "person.address", source = "address")
    @Mapping(target = "person.phone", source = "phone")
    Customer toEntity(CustomerDTO customerDTO);

    @Mapping(source = "person.name", target = "name")
    @Mapping(source = "person.gender", target = "gender")
    @Mapping(source = "person.age", target = "age")
    @Mapping(source = "person.identification", target = "identification")
    @Mapping(source = "person.address", target = "address")
    @Mapping(source = "person.phone", target = "phone")
    CustomerDTO toDTO(Customer customer);

    @Mapping(target = "person.name", source = "name")
    @Mapping(target = "person.gender", source = "gender")
    @Mapping(target = "person.age", source = "age")
    @Mapping(target = "person.address", source = "address")
    @Mapping(target = "person.phone", source = "phone")
    void updateCustomerFromDto(CustomerDTO customerDTO, @MappingTarget Customer customer);
}
