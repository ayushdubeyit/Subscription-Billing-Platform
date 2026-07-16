package com.ayush.subscription.customer.exception;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String email) {
        super("Email Already Exists :" + email);
    }
}
