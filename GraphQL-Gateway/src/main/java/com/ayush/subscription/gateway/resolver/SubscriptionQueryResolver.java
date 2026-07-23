package com.ayush.subscription.gateway.resolver;

import com.ayush.subscription.gateway.client.SubscriptionServiceClient;
import com.ayush.subscription.gateway.dto.subscription.SubscriptionDto;
import com.ayush.subscription.gateway.dto.subscription.SubscriptionPlanDto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SubscriptionQueryResolver {

    private final SubscriptionServiceClient subscriptionServiceClient;

    public SubscriptionQueryResolver(
            SubscriptionServiceClient subscriptionServiceClient) {

        this.subscriptionServiceClient = subscriptionServiceClient;
    }


    @QueryMapping
    public SubscriptionPlanDto planByUuid(
            @Argument String planUuid){

        return subscriptionServiceClient.getPlanByUuid(planUuid);
    }

    @QueryMapping
    public List<SubscriptionPlanDto> allPlans(
            @Argument int page,
            @Argument int size){

        return subscriptionServiceClient.getAllPlans(page,size);
    }
    @QueryMapping
    public SubscriptionDto subscriptionByUuid(
            @Argument String subscriptionUuid) {

        return subscriptionServiceClient.getSubscriptionByUuid(
                subscriptionUuid
        );
    }
    @QueryMapping
    public List<SubscriptionDto> subscriptionsByCustomer(
            @Argument String customerUuid) {

        return subscriptionServiceClient.getSubscriptionsByCustomer(
                customerUuid
        );
    }
}
