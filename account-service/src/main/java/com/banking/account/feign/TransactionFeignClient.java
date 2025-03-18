package com.banking.account.feign;

import com.banking.account.model.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "transaction-service")
public interface TransactionFeignClient {

    @GetMapping("/transaction/all")
    List<Transaction> getTransactionsByAccountId(@RequestParam long accountId);
} 