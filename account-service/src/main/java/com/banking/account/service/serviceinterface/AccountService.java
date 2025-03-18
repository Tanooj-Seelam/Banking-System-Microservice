package com.banking.account.service.serviceinterface;

import com.banking.account.model.Account;

public interface AccountService {
    
    Account getAccount(String sortCode, String accountNumber);
    
    Account getAccount(String accountNumber);
    
    Account createAccount(String bankName, String ownerName);
    
    boolean updateBalance(long accountId, double amount);

    boolean deleteAccount(String accountNumber);
} 