package com.codingtest.service;

import static org.junit.jupiter.api.Assertions.*;

import com.codingtest.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TransactionServiceTest {

  @Autowired
  TransactionRepository transactionRepository;

  @Test
  void findAll() {
    assertEquals(2, transactionRepository.findAll().size());
  }
}