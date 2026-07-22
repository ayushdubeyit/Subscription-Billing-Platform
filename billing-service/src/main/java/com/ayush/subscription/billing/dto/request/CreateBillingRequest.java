package com.ayush.subscription.billing.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record  CreateBillingRequest (

        @NotNull(message = "Customer UUID is required")
        UUID customerUuid,

        @NotNull(message = "Subscription UUID is required")
        UUID subscriptionUuid,

        @NotNull(message = "Base amount is required")
        @Positive(message = "Base amount must be greater than 0")
        BigDecimal baseAmount,

        @NotNull(message = "Discount is required")
        @PositiveOrZero(message = "Discount cannot be negative")
        BigDecimal discount,

        @NotNull(message = "Tax is required")
        @PositiveOrZero(message = "Tax cannot be negative")
        BigDecimal tax,

        @NotBlank(message = "Currency is required")
        String currency

){

}
