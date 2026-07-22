package com.ayush.subscription.billing.exception;

import java.util.UUID;

public class BillingNotFoundException extends RuntimeException{

    public BillingNotFoundException(UUID billingUuid) {
        super("Billing not found with UUID: " + billingUuid);
    }
}
