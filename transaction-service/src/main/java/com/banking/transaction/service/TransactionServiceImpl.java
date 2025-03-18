package com.banking.transaction.service;

import com.banking.transaction.exception.CustomException;
import com.banking.transaction.repository.TransactionRepository;
import com.banking.transaction.service.serviceinterface.TransactionService;
import com.banking.transaction.util.TransactionConstants;
import com.banking.transaction.entity.TransactionInput;
import com.banking.transaction.feign.AccountFeignClient;
import com.banking.transaction.model.Account;
import com.banking.transaction.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private AccountFeignClient accountFeignClient;

    @Override
    public List<Transaction> findAllByAccountId(long accountId) {
        try {
            return transactionRepository.findAllBySourceAccountIdOrTargetAccountId(accountId, accountId);
        } catch (Exception e) {
            throw new CustomException(TransactionConstants.NO_TRANSACTIONS_FOUND);
        }
    }

    @Override
    public Transaction makeTransfer(TransactionInput transactionInput) {
        try {
            String sourceAccountNumber = transactionInput.getSourceAccountNumber();
            String targetAccountNumber = transactionInput.getTargetAccountNumber();
            double amount = transactionInput.getAmount();

            if (amount <= 0) {
                throw new CustomException(TransactionConstants.INVALID_AMOUNT);
            }

            // Get source and target accounts
            Account sourceAccount = accountFeignClient.getAccountByAccountNumber(sourceAccountNumber);
            Account targetAccount = accountFeignClient.getAccountByAccountNumber(targetAccountNumber);

            if (sourceAccount == null || targetAccount == null) {
                throw new CustomException(TransactionConstants.ACCOUNT_NOT_FOUND);
            }

            // Check if source account has sufficient balance
            if (sourceAccount.getCurrentBalance() < amount) {
                throw new CustomException(TransactionConstants.INSUFFICIENT_ACCOUNT_BALANCE);
            }

            // Update account balances
            boolean sourceUpdateSuccess = accountFeignClient.updateBalance(sourceAccount.getId(), -amount);
            boolean targetUpdateSuccess = accountFeignClient.updateBalance(targetAccount.getId(), amount);

            if (!sourceUpdateSuccess || !targetUpdateSuccess) {
                // Rollback if either update fails
                if (sourceUpdateSuccess) {
                    accountFeignClient.updateBalance(sourceAccount.getId(), amount);
                }
                if (targetUpdateSuccess) {
                    accountFeignClient.updateBalance(targetAccount.getId(), -amount);
                }
                throw new CustomException(TransactionConstants.BALANCE_UPDATE_FAILED);
            }

            // Create and save transaction
            Transaction transaction = Transaction.builder()
                .sourceAccountId(sourceAccount.getId())
                .targetAccountId(targetAccount.getId())
                .targetOwnerName(targetAccount.getOwnerName())
                .amount(amount)
                .initiationDate(LocalDateTime.now())
                .completionDate(LocalDateTime.now())
                .reference(transactionInput.getReference())
                .status("COMPLETED")
                .build();

            return transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new CustomException(TransactionConstants.TRANSACTION_FAILED);
        }
    }
}