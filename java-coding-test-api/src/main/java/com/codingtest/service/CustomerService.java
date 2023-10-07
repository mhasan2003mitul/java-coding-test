package com.codingtest.service;

import com.codingtest.model.Account;
import com.codingtest.model.Customer;
import com.codingtest.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  public Customer findById(Long customerId) {
    return customerRepository.findById(customerId).orElse(null);
  }

  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  public Customer save(Customer customer) {
    Customer customer1 = customerRepository.save(customer);
    customerRepository.flush();
    return customer1;
  }

  public Account findCurrentAccountByCustomerId(Long customerId) {
    return customerRepository.findCurrentAccountByCustomerId(customerId);
  }

  public List<Account> findAccountByCustomerId(Long customerId) {
    return customerRepository.findAccountByCustomerId(customerId);
  }
}
