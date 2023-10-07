package com.codingtest.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.codingtest.model.Account;
import com.codingtest.model.AccountType;
import com.codingtest.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AccountRepositoryTest {

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Test
  void findByAccountType() {
    assertEquals(1, accountRepository.findByAccountType(AccountType.SAVING_ACCOUNT).size());
    assertEquals(1, accountRepository.findByAccountType(AccountType.CURRENT_ACCOUNT).size());
  }

  @Test
  void findAll() {
    assertEquals(2, accountRepository.findAll().size());
  }

  @Test
  void saveAccount() {
    Customer customer = customerRepository.findById(Long.valueOf(3)).orElse(null);
    Account account = Account.builder().accountType(AccountType.CURRENT_ACCOUNT).balance(1000).build();
    customer.getAccounts().add(account);
    customerRepository.save(customer);
    assertEquals(3, accountRepository.findAll().size());
  }
}