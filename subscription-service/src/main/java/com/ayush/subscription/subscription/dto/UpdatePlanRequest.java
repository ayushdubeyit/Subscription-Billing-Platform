package com.ayush.subscription.subscription.dto;

import com.ayush.subscription.subscription.enums.BillingCycle;
import com.ayush.subscription.subscription.enums.PlanStatus;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePlanRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private BillingCycle billingCycle;

    private PlanStatus status;
}
