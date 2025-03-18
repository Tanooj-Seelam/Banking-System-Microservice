package com.banking.transaction.feign;

import com.banking.transaction.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service")
public interface AccountFeignClient {

    @GetMapping("/account/balance")
    Account getAccountByAccountNumber(@RequestParam String accountNumber);

    @PutMapping("/account/balance")
    boolean updateBalance(@RequestParam long accountId, @RequestParam double amount);
} 