package com.ayush.subscription.gateway.dto.billing;

import java.math.BigDecimal;

public record InvoiceDto(

        String invoiceUuid,

        String invoiceNumber,

        BigDecimal amount,

        InvoiceStatus status,

        String issuedAt
) {
}
