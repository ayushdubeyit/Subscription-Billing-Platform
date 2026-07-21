package com.ayush.subscription.payment.dto.request;

import com.ayush.subscription.payment.enums.PaymentMethod;
import com.ayush.subscription.payment.enums.PaymentStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

    @NotNull(message = "Subscription UUID is required!")
    private UUID subscriptionUuid;

    @NotNull(message = "customer UUID is required!")
    private UUID customerUuid;

    @NotNull(message = "Payment Method is required!")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Amount is required!")
    @DecimalMin(value = "0.01" , message = "Amount must be greater than zero")
    private BigDecimal amount;


    @NotNull(message = "Currency is required")
    private String currency;

}
