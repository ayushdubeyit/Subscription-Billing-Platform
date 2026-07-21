package com.ayush.subscription.payment.dto.request;

import com.ayush.subscription.payment.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdatePaymentStatusRequest {
    @NotNull(message = "Payment status is required")
    private PaymentStatus status;
}
