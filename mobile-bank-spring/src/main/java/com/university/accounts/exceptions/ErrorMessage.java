package com.university.accounts.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorMessage {

    // Not found
    USER_BY_USERNAME_NOT_FOUND("User with such username"),
    USER_BY_PHONE_NOT_FOUND(""),
    USER_NOT_FOUND("User not found"),
    DEFAULT_ACCOUNT_NOT_FOUND("User has not any account"),
    NOT_ALLOWED_TO_VIEW_ACCOUNT("Not eligible to view this account"),
    // No permissions
    USER_NOT_AUTHORIZED("User not authorized"),
    // Bad request
    INVALID_ACCOUNT("Invalid account");
    @Getter
    private final String message;
}
