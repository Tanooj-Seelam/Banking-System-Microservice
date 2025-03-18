package com.banking.transaction.service.serviceinterface;

import com.banking.transaction.model.Transaction;
import com.banking.transaction.entity.TransactionInput;

import java.util.List;

public interface TransactionService {
    
    List<Transaction> findAllByAccountId(long accountId);
    
    Transaction makeTransfer(TransactionInput transactionInput);
} 