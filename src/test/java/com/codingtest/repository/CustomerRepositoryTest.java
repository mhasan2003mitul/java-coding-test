package com.codingtest.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.codingtest.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CustomerRepositoryTest {

  @Autowired
  CustomerRepository customerRepository;

  @Test
  void findCurrentAccountByCustomerId() {
    assertNotNull(customerRepository.findCurrentAccountByCustomerId(Long.valueOf(1)));
    assertNull(customerRepository.findCurrentAccountByCustomerId(Long.valueOf(3)));
  }

  @Test
  void findAll() {
    assertEquals(5, customerRepository.findAll().size());
  }

  @Test
  void saveCustomer() {
    Customer customer = Customer.builder().name("Test").surname("Test-Surname").build();
    customerRepository.save(customer);
    assertEquals(6, customerRepository.findAll().size());
  }
}