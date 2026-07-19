package com.ayush.subscription.subscription.dto;

import com.ayush.subscription.subscription.enums.BillingCycle;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePlanRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private BillingCycle billingCycle;
}
