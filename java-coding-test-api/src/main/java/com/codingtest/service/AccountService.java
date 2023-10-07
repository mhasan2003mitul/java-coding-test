package com.codingtest.service;

import com.codingtest.model.Account;
import com.codingtest.model.AccountType;
import com.codingtest.model.Transaction;
import com.codingtest.repository.AccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  public List<Account> findAll() {
    return accountRepository.findAll();
  }

  public List<Account> findByAccountType(AccountType accountType) {
    return accountRepository.findByAccountType(accountType);
  }

  public List<Transaction> findTransactionsByAccountId(Long accountId) {
    return accountRepository.findTransactionsByAccountId(accountId);
  }
}
