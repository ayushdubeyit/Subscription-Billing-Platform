package com.ayush.subscription.billing.exception;

import java.util.UUID;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException(UUID invoiceUuid) {
        super("Invoice not found with UUID: " + invoiceUuid);
    }
}
