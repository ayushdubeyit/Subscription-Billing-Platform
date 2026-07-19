package com.ayush.subscription.subscription.util;

import com.ayush.subscription.subscription.dto.SubscriptionResponse;
import com.ayush.subscription.subscription.entity.Subscription;
import org.springframework.stereotype.Component;


@Component
public class SubscriptionHelper   {
    public static SubscriptionResponse toResponse(Subscription subscription){

        return SubscriptionResponse.builder()
                .subscriptionUuid(subscription.getSubscriptionUuid())
                .customerUuid(subscription.getCustomerUuid())
                .planUuid(subscription.getPlanUuid())
                .status(subscription.getStatus())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .nextBillingDate(subscription.getNextBillingDate())
                .autoRenew(subscription.getAutoRenew())
                .build();
    }
}
