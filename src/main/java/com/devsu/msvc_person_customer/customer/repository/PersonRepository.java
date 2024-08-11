package com.devsu.msvc_person_customer.customer.repository;

import com.devsu.msvc_person_customer.customer.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,String> {


}
