package com.ayush.subscription.subscription.dto;

import com.ayush.subscription.subscription.enums.BillingCycle;
import com.ayush.subscription.subscription.enums.PlanStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionPlanResponse {

    private UUID planUuid;

    private String name;

    private String description;

    private BigDecimal price;

    private BillingCycle billingCycle;

    private PlanStatus status;


}
