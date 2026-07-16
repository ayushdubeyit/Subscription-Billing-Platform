package com.ayush.subscription.customer.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(UUID custometrUuid) {
        super("Customer Not Found with this UUid " +  custometrUuid);
    }
}
