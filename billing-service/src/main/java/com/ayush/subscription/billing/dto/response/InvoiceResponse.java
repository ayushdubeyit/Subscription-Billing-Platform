package com.ayush.subscription.billing.dto.response;

import com.ayush.subscription.billing.enums.InvoiceStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record InvoiceResponse(


        UUID invoiceUuid,

        String invoiceNumber,

        BigDecimal amount,

        InvoiceStatus status,

        LocalDateTime issuedAt


) {

}
