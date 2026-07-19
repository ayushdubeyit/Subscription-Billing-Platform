package com.ayush.subscription.subscription.graphql.mutation;

import com.ayush.subscription.subscription.dto.CreatePlanRequest;
import com.ayush.subscription.subscription.dto.SubscriptionPlanResponse;
import com.ayush.subscription.subscription.dto.UpdatePlanRequest;
import com.ayush.subscription.subscription.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;


@Controller
@RequiredArgsConstructor
public class SubscriptionPlanMutation {


    private final SubscriptionPlanService subscriptionPlanService;

    @MutationMapping
    public SubscriptionPlanResponse createPlan(
            @Argument("input") CreatePlanRequest request) {

        return subscriptionPlanService.createPlan(request);
    }

    @MutationMapping
    public SubscriptionPlanResponse updatePlan(
            @Argument UUID planUuid,
            @Argument("input") UpdatePlanRequest request) {

        return subscriptionPlanService.updatePlan(planUuid, request);
    }

    @MutationMapping
    public Boolean deletePlan(@Argument UUID planUuid) {

        return subscriptionPlanService.deletePlan(planUuid);
    }
}
