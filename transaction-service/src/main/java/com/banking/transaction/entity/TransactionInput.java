package com.banking.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionInput {

    @NotBlank(message = "Source account number is mandatory")
    private String sourceAccountNumber;

    @NotBlank(message = "Target account number is mandatory")
    private String targetAccountNumber;

    @Positive(message = "Amount must be positive")
    private double amount;

    private String reference;
} 