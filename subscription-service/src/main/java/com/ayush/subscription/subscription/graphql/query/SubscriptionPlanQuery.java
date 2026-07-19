package com.ayush.subscription.subscription.graphql.query;


import com.ayush.subscription.subscription.dto.SubscriptionPlanResponse;
import com.ayush.subscription.subscription.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SubscriptionPlanQuery {

    private final SubscriptionPlanService subscriptionPlanService;

    @QueryMapping
    public SubscriptionPlanResponse planByUuid(@Argument UUID planUuid) {
        return subscriptionPlanService.getPlanByUuid(planUuid);
    }

    @QueryMapping
    public List<SubscriptionPlanResponse> allPlans(@Argument int page,
                                                   @Argument int size) {
        return subscriptionPlanService.getAllPlans(page, size);
    }
}
