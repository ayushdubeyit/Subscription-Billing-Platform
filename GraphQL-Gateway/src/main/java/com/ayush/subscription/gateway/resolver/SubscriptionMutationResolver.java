package com.ayush.subscription.gateway.resolver;

import com.ayush.subscription.gateway.client.SubscriptionServiceClient;
import com.ayush.subscription.gateway.dto.subscription.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SubscriptionMutationResolver {


    private final SubscriptionServiceClient subscriptionServiceClient;

    public SubscriptionMutationResolver(
            SubscriptionServiceClient subscriptionServiceClient) {

        this.subscriptionServiceClient = subscriptionServiceClient;
    }

    @MutationMapping
    public SubscriptionPlanDto createPlan(
            @Argument CreatePlanInput input){

        return subscriptionServiceClient.createPlan(input);
    }

    @MutationMapping
    public SubscriptionPlanDto updatePlan(
            @Argument String planUuid,
            @Argument UpdatePlanInput input){

        return subscriptionServiceClient.updatePlan(planUuid,input);
    }

    @MutationMapping
    public Boolean deletePlan(
            @Argument String planUuid){

        return subscriptionServiceClient.deletePlan(planUuid);
    }
    @MutationMapping
    public SubscriptionDto createSubscription(
            @Argument CreateSubscriptionInput input) {

        return subscriptionServiceClient.createSubscription(input);
    }
    @MutationMapping
    public SubscriptionDto updateSubscription(
            @Argument String subscriptionUuid,
            @Argument UpdateSubscriptionInput input) {

        return subscriptionServiceClient.updateSubscription(
                subscriptionUuid,
                input
        );
    }

    @MutationMapping
    public Boolean cancelSubscription(
            @Argument String subscriptionUuid) {

        return subscriptionServiceClient.cancelSubscription(
                subscriptionUuid
        );
    }

}
