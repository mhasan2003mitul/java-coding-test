package com.codingtest.repository;

import com.codingtest.model.Account;
import com.codingtest.model.AccountType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
  List<Account> findByAccountType(AccountType accountType);

}
