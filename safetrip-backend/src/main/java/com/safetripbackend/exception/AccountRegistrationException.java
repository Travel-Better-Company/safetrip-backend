package com.safetripbackend.exception;

public class AccountRegistrationException extends RuntimeException {
    public AccountRegistrationException(String message) {
        super(message);
    }
}
