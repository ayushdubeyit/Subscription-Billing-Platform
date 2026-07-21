package com.ayush.subscription.billing.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record  CreateBillingRequest (

        @NotNull
        UUID customerUuid,

        @NotNull
        UUID subscriptionUuid,

        @NotNull
        @DecimalMin("0.0")
        BigDecimal baseAmount,

        @NotNull
        BigDecimal discount,

        @NotNull
        BigDecimal tax,

        @NotNull
        String currency

){

}
