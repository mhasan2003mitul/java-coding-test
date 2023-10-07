package com.codingtest.repository;

import com.codingtest.model.Account;
import com.codingtest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query("select Account from (select cus.accounts as Account from Customer cus where cus.customerId = :customerId) where Account.accountType = 'CURRENT_ACCOUNT'")
  Account findCurrentAccountByCustomerId(Long customerId);
}
