package com.ayush.subscription.payment.exception;

public class InvalidPaymentStateException extends RuntimeException{
    public InvalidPaymentStateException(String message) {
        super(message);
    }
}
