package com.ayush.subscription.subscription.util;

import com.ayush.subscription.subscription.dto.SubscriptionPlanResponse;
import com.ayush.subscription.subscription.entity.SubscriptionPlan;

public class PlanHelper {
    public static SubscriptionPlanResponse toResponse(SubscriptionPlan plan) {

        return SubscriptionPlanResponse.builder()
                .planUuid(plan.getPlanUuid())
                .name(plan.getName())
                .description(plan.getDescription())
                .price(plan.getPrice())
                .billingCycle(plan.getBillingCycle())
                .status(plan.getStatus())
                .build();
    }

}
