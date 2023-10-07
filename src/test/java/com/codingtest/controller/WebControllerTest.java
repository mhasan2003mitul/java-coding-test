package com.codingtest.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.codingtest.model.Account;
import com.codingtest.model.Customer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebControllerTest {

  @Value(value="${local.server.port}")
  private int port;

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void getAllCustomer() {
    ResponseEntity<List> responseEntity = restTemplate.getForEntity("/customer", List.class);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(5, responseEntity.getBody().size());
  }

  @Test
  void getAllAccount() {
    ResponseEntity<List> responseEntity = restTemplate.getForEntity("/account", List.class);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(2, responseEntity.getBody().size());
  }

  @Test
  void getAllTransaction() {
    ResponseEntity<List> responseEntity = restTemplate.getForEntity("/transaction", List.class);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertTrue(responseEntity.getBody().size() >= 2);
  }

  @Test
  void getAllCurrentAccount() {
    ResponseEntity<List> responseEntity = restTemplate.getForEntity("/currentaccount", List.class);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertTrue(responseEntity.getBody().size() >= 1);
  }

  @Test
  void getCurrentAccountByCustomerId() {
    ResponseEntity<Account> responseEntity = restTemplate.getForEntity("/currentaccount/1", Account.class);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getAccountId());
  }

  @Test
  void createCurrentAccount() {
    ResponseEntity<Account> responseEntity = restTemplate.postForEntity("/currentaccount/3/1000", null, Account.class);
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals(3, responseEntity.getBody().getAccountId());
  }
}