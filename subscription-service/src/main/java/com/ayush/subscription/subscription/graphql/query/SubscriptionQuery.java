package com.ayush.subscription.subscription.graphql.query;


import com.ayush.subscription.subscription.dto.SubscriptionResponse;
import com.ayush.subscription.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SubscriptionQuery {
    private final SubscriptionService subscriptionService;

    @QueryMapping
    public SubscriptionResponse subscriptionByUuid(
            @Argument UUID subscriptionUuid) {

        return subscriptionService.getSubscriptionByUuid(subscriptionUuid);
    }

    @QueryMapping
    public List<SubscriptionResponse> subscriptionsByCustomer(
            @Argument UUID customerUuid) {

        return subscriptionService.getSubscriptionsByCustomer(customerUuid);
    }
}
