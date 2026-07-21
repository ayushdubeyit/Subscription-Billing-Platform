package com.ayush.subscription.billing.util;

import com.ayush.subscription.billing.dto.response.InvoiceResponse;
import com.ayush.subscription.billing.entity.Billing;
import com.ayush.subscription.billing.entity.Invoice;
import com.ayush.subscription.billing.enums.InvoiceStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public final class InvoiceHelper {
    private InvoiceHelper() {
    }
    public static Invoice buildInvoice(Billing billing, String invoiceNumber) {

        return Invoice.builder()
                .invoiceUuid(UUID.randomUUID())
                .invoiceNumber(invoiceNumber)
                .billing(billing)
                .amount(billing.getTotalAmount())
                .status(InvoiceStatus.GENERATED)
                .issuedAt(LocalDateTime.now())
                .build();
    }
    public static InvoiceResponse toResponse(Invoice invoice) {

        return new InvoiceResponse(
                invoice.getInvoiceUuid(),
                invoice.getInvoiceNumber(),
                invoice.getAmount(),
                invoice.getStatus(),
                invoice.getIssuedAt()
        );
    }
}
