package com.ayush.subscription.billing.dto.response;

import com.ayush.subscription.billing.enums.BillingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record BillingResponse(

        UUID billingUuid,

        UUID customerUuid,

        UUID subscriptionUuid,

        BigDecimal totalAmount,

        String currency,

        BillingStatus status,

        LocalDateTime createdAt
) {
}
