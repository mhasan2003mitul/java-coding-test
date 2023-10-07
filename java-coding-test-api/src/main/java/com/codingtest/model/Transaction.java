package com.codingtest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction")
@JsonInclude(Include.NON_NULL)
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transactionId;
  @ManyToOne
  @JoinColumn(name = "accountId", nullable = false)
  @JsonIgnoreProperties({"transactions"})
  private Account account;
  @Enumerated(EnumType.STRING)
  private TransactionType transactionType;
  @Column(name="balance", columnDefinition = "Decimal(10,2) default '0.0'")
  private double amount;
}
