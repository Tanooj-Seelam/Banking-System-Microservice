package com.banking.account.util;

import com.banking.account.entity.AccountInput;
import com.banking.account.entity.CreateAccountInput;
import org.springframework.stereotype.Component;

@Component
public class InputValidator {

    public static boolean isAccountNoValid(String accountNo) {
        return accountNo != null && AccountConstants.ACCOUNT_NUMBER_PATTERN.matcher(accountNo).matches();
    }

    public static boolean isSortCodeValid(String sortCode) {
        return sortCode != null && AccountConstants.SORT_CODE_PATTERN.matcher(sortCode).matches();
    }

    public static boolean isSearchCriteriaValid(AccountInput accountInput) {
        return accountInput != null && isSortCodeValid(accountInput.getSortCode())
                && isAccountNoValid(accountInput.getAccountNumber());
    }

    public static boolean isCreateAccountCriteriaValid(CreateAccountInput createAccountInput) {
        return createAccountInput != null && !createAccountInput.getBankName().isBlank()
                && !createAccountInput.getOwnerName().isBlank();
    }
} 