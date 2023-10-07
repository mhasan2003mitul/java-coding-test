package com.codingtest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accountId;
  @Enumerated(EnumType.STRING)
  private AccountType accountType;
  @Column(name="balance", columnDefinition = "Decimal(10,2) default '0.0'")
  private double balance;
  @ManyToMany(mappedBy = "accounts")
  @JsonIgnoreProperties({"accounts"})
  private List<Customer> customers = new ArrayList<>();
  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  @JsonIgnoreProperties({"account"})
  private List<Transaction> transactions = new ArrayList<>();
}
