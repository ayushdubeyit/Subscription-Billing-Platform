package com.ayush.subscription.billing.service.interfaces;

import com.ayush.subscription.billing.dto.response.InvoiceResponse;

import java.util.UUID;

public interface InvoiceService {
    InvoiceResponse getInvoice(UUID invoiceUuid);


}
