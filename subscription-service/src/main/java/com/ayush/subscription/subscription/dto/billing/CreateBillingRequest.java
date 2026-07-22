package com.ayush.subscription.subscription.dto.billing;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBillingRequest {
    private UUID customerUuid;

    private UUID subscriptionUuid;

    private BigDecimal baseAmount;

    private BigDecimal discount;

    private BigDecimal tax;

    private String currency;
}
