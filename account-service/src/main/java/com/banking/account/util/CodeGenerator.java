package com.banking.account.util;

import com.mifmif.common.regex.Generex;

public class CodeGenerator {
    private static final String SORT_CODE_PATTERN_STRING = "[0-9]{2}-[0-9]{2}-[0-9]{2}";
    private static final String ACCOUNT_NUMBER_PATTERN_STRING = "[0-9]{8}";
    
    private final Generex sortCodeGenerex = new Generex(SORT_CODE_PATTERN_STRING);
    private final Generex accountNumberGenerex = new Generex(ACCOUNT_NUMBER_PATTERN_STRING);

    public CodeGenerator() {}

    public String generateSortCode() {
        return sortCodeGenerex.random();
    }

    public String generateAccountNumber() {
        return accountNumberGenerex.random();
    }
} 