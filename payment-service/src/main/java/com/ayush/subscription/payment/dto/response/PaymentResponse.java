package com.ayush.subscription.payment.dto.response;

import com.ayush.subscription.payment.enums.PaymentMethod;
import com.ayush.subscription.payment.enums.PaymentStatus;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private UUID paymentUuid;

    private UUID subscriptionUuid;

    private UUID customerUuid;

    private BigDecimal amount;

    private String currency;

    private PaymentMethod paymentMethod;

    private PaymentStatus status;

    private String transactionId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
