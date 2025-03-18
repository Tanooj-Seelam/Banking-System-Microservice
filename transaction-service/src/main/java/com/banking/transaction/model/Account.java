package com.banking.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    private long id;
    private String sortCode;
    private String accountNumber;
    private double currentBalance;
    private String bankName;
    private String ownerName;
} 