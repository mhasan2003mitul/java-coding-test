package com.codingtest.controller;

import com.codingtest.exception.AccountNotFoundException;
import com.codingtest.exception.CustomerCurrentAccountAlreadyExistsException;
import com.codingtest.exception.CustomerNotFoundException;
import com.codingtest.model.Account;
import com.codingtest.model.AccountType;
import com.codingtest.model.Customer;
import com.codingtest.model.Transaction;
import com.codingtest.model.TransactionType;
import com.codingtest.service.AccountService;
import com.codingtest.service.CustomerService;
import com.codingtest.service.TransactionService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class WebController {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private TransactionService transactionService;

  @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Customer> getAllCustomer() {
    return customerService.findAll();
  }

  @GetMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Account> getAllAccount() {
    return accountService.findAll();
  }

  @GetMapping(value = "/account/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Account> getAllAccount(@PathVariable Long customerId) {
    return customerService.findAccountByCustomerId(customerId);
  }

  @GetMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Transaction> getAllTransaction() {
    return transactionService.findAll();
  }

  @GetMapping(value = "/transaction/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Transaction> getTransactionByAccountId(@PathVariable Long accountId) {
    return accountService.findTransactionsByAccountId(accountId);
  }

  @PostMapping(value = "/currentaccount/{customerId}/{initialCredit}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Account> createCurrentAccount(@PathVariable Long customerId, @PathVariable double initialCredit) {
    Customer customer = customerService.findById(customerId);
    if(customer != null) {
      Account customerCurrentAccount = customerService.findCurrentAccountByCustomerId(customerId);
      if(customerCurrentAccount == null) {
        customerCurrentAccount = new Account();
        customerCurrentAccount.setAccountType(AccountType.CURRENT_ACCOUNT);
        customerCurrentAccount.setBalance(initialCredit);
        customer.getAccounts().add(customerCurrentAccount);
        Transaction transaction = new Transaction();
        transaction.setAccount(customerCurrentAccount);
        transaction.setTransactionType(TransactionType.DEBIT);
        transaction.setAmount(initialCredit);
        customerCurrentAccount.getTransactions().add(transaction);
        customerService.save(customer);
      } else {
        throw new CustomerCurrentAccountAlreadyExistsException("Current account creation failed. Current account already exists for the customer");
      }
    } else {
      throw new CustomerNotFoundException("Customer does not exists.");
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(customerService.findCurrentAccountByCustomerId(customerId));
  }

  @GetMapping(value = "/currentaccount", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Account> getAllCurrentAccount() {
    return accountService.findByAccountType(AccountType.CURRENT_ACCOUNT);
  }

  @GetMapping(value = "/currentaccount/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Account getCurrentAccountByCustomerId(@PathVariable Long customerId) {
    Account account = customerService.findCurrentAccountByCustomerId(customerId);
    if(customerService.findById(customerId) == null) {
      throw new CustomerNotFoundException("Customer does not exists.");
    }
    if(account == null) {
      throw new AccountNotFoundException("No current account is found for this customer.");
    }
    return account;
  }

}
