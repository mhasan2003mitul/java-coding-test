package com.codingtest.service;

import com.codingtest.model.Transaction;
import com.codingtest.repository.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  @Autowired
  TransactionRepository transactionRepository;

  public List<Transaction> findAll() {
    return transactionRepository.findAll();
  }

}
