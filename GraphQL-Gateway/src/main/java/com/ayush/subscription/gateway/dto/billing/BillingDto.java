package com.ayush.subscription.gateway.dto.billing;

import java.math.BigDecimal;

public record BillingDto(

        String billingUuid,

        String customerUuid,

        String subscriptionUuid,

        BigDecimal totalAmount,

        String currency,

        BillingStatus status,

        String createdAt
) {
}
