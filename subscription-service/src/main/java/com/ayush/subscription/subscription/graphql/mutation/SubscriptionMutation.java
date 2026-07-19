package com.ayush.subscription.subscription.graphql.mutation;

import com.ayush.subscription.subscription.dto.CreateSubscriptionRequest;
import com.ayush.subscription.subscription.dto.SubscriptionResponse;
import com.ayush.subscription.subscription.dto.UpdateSubscriptionRequest;
import com.ayush.subscription.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SubscriptionMutation {

    private final SubscriptionService subscriptionService;

    @MutationMapping
    public SubscriptionResponse createSubscription(
            @Argument("input") CreateSubscriptionRequest request) {

        return subscriptionService.createSubscription(request);
    }

    @MutationMapping
    public SubscriptionResponse updateSubscription(
            @Argument UUID subscriptionUuid,
            @Argument("input") UpdateSubscriptionRequest request) {

        return subscriptionService.updateSubscription(subscriptionUuid, request);
    }

    @MutationMapping
    public Boolean cancelSubscription(
            @Argument UUID subscriptionUuid) {

        return subscriptionService.cancelSubscription(subscriptionUuid);
    }

}
