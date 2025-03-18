package com.banking.account.controller;

import com.banking.account.service.serviceinterface.AccountService;
import com.banking.account.model.Account;
import com.banking.account.entity.AccountInput;
import com.banking.account.entity.CreateAccountInput;
import com.banking.account.util.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.banking.account.util.AccountConstants.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/balance")
    public ResponseEntity<Object> checkAccountBalance(@Valid @RequestBody AccountInput accountInput) {
        try {
            if (InputValidator.isSearchCriteriaValid(accountInput)) {
                Account account = accountService.getAccount(accountInput.getSortCode(), accountInput.getAccountNumber());
                if (account == null) {
                    return ResponseEntity.ok(NO_ACCOUNT_FOUND);
                } else {
                    return ResponseEntity.ok(account);
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_SEARCH_CRITERIA);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createAccount(@Valid @RequestBody CreateAccountInput createAccountInput) {
        try {
            if (InputValidator.isCreateAccountCriteriaValid(createAccountInput)) {
                Account account = accountService.createAccount(
                        createAccountInput.getBankName(), createAccountInput.getOwnerName());
                if (account == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CREATE_ACCOUNT_FAILED);
                } else {
                    return ResponseEntity.ok(account);
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_SEARCH_CRITERIA);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping("/details")
    public ResponseEntity<Object> getAccountByAccountNumber(@RequestParam String accountNumber) {
        try {
            Account account = accountService.getAccount(accountNumber);
            if (account == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PutMapping("/balance")
    public ResponseEntity<Object> updateBalance(@RequestParam long accountId, @RequestParam double amount) {
        try {
            boolean success = accountService.updateBalance(accountId, amount);
            return ResponseEntity.status(success ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(success);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteAccount(@RequestParam String accountNumber) {
        try {
            boolean delete = accountService.deleteAccount(accountNumber);
            return ResponseEntity.status(delete ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(delete);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}