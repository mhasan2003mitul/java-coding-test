package com.codingtest.repository;

import com.codingtest.model.Account;
import com.codingtest.model.AccountType;
import com.codingtest.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
  List<Account> findByAccountType(AccountType accountType);

  @Query("select acc.transactions from Account acc where acc.accountId = :accountId")
  List<Transaction> findTransactionsByAccountId(Long accountId);
}
