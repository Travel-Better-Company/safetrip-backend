package com.safetripbackend.subscription.execption;

public class SubscriptionAlreadyExistsException extends RuntimeException {
    public SubscriptionAlreadyExistsException(String message) {
        super(message);
    }
}