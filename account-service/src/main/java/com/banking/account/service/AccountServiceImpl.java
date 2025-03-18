package com.banking.account.service;

import com.banking.account.feign.TransactionFeignClient;
import com.banking.account.model.Account;
import com.banking.account.repository.AccountRepository;
import com.banking.account.service.serviceinterface.AccountService;
import com.banking.account.exception.CustomException;
import com.banking.account.util.CodeGenerator;
import com.banking.account.util.AccountConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private TransactionFeignClient transactionFeignClient;

    @Override
    public Account getAccount(String sortCode, String accountNumber) {
        try {
            Optional<Account> account = accountRepository.findBySortCodeAndAccountNumber(sortCode, accountNumber);
            if (account.isPresent()) {
                Account foundAccount = account.get();
                foundAccount.setTransactions(transactionFeignClient.getTransactionsByAccountId(foundAccount.getId()));
                return foundAccount;
            } else {
                throw new CustomException(AccountConstants.NO_ACCOUNT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException(AccountConstants.ACCOUNT_FETCH_FAILED);
        }
    }

    @Override
    public Account getAccount(String accountNumber) {
        try {
            Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
            if (account.isPresent()) {
                return account.get();
            } else {
                throw new CustomException(AccountConstants.NO_ACCOUNT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException(AccountConstants.ACCOUNT_FETCH_FAILED);
        }
    }

    @Override
    public Account createAccount(String bankName, String ownerName) {
        try {
            CodeGenerator codeGenerator = new CodeGenerator();
            Account newAccount = new Account(bankName, ownerName, codeGenerator.generateSortCode(), 
                    codeGenerator.generateAccountNumber(), 0.00);
            return accountRepository.save(newAccount);
        } catch (Exception e) {
            throw new CustomException(AccountConstants.CREATE_ACCOUNT_FAILED);
        }
    }
    
    @Override
    public boolean updateBalance(long accountId, double amount) {
        try {
            Optional<Account> accountOptional = accountRepository.findById(accountId);
            
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                double newBalance = account.getCurrentBalance() + amount;
                
                // Prevent negative balance
                if (newBalance < 0) {
                    throw new CustomException(AccountConstants.INSUFFICIENT_FUNDS);
                }
                
                account.setCurrentBalance(newBalance);
                accountRepository.save(account);
                return true;
            } else {
                throw new CustomException(AccountConstants.NO_ACCOUNT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException(AccountConstants.UPDATE_BALANCE_FAILED);
        }
    }

    @Override
    public boolean deleteAccount(String accountNumber) {
        try {
            Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
            if (account.isPresent()) {
                accountRepository.delete(account.get());
                return true;
            } else {
                throw new CustomException(AccountConstants.NO_ACCOUNT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException(AccountConstants.ACCOUNT_DELETION_FAILED);
        }
    }
} 