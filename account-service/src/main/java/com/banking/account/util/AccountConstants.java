package com.banking.account.util;

import java.util.regex.Pattern;

public class AccountConstants {

    public static final String NO_ACCOUNT_FOUND = "No account found with the given details";
    public static final String CREATE_ACCOUNT_FAILED = "Failed to create account";
    public static final String INVALID_SEARCH_CRITERIA = "Invalid search criteria";
    public static final String INSUFFICIENT_FUNDS = "Insufficient funds in the account";
    public static final String INVALID_AMOUNT = "Amount must be greater than zero";
    public static final String UPDATE_BALANCE_FAILED = "Failed to update account balance";
    public static final String GENERIC_ERROR = "An unexpected error occurred";
    public static final Pattern SORT_CODE_PATTERN = Pattern.compile("^[0-9]{2}-[0-9]{2}-[0-9]{2}$");
    public static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("^[0-9]{8}$");
    public static final String ACCOUNT_DELETION_FAILED = "Unable to delete the Account";
    public static final String ACCOUNT_FETCH_FAILED = "Unable to locate the Account Details";
}