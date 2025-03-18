package com.banking.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_sequence", initialValue = 5)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private long id;
    
    private long sourceAccountId;
    private long targetAccountId;
    private String targetOwnerName;
    private double amount;
    private LocalDateTime initiationDate;
    private LocalDateTime completionDate;
    private String reference;
    private String status;
    private Double latitude;
    private Double longitude;
} 