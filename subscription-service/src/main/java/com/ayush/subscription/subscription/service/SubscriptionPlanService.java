package com.ayush.subscription.subscription.service;

import com.ayush.subscription.subscription.dto.CreatePlanRequest;
import com.ayush.subscription.subscription.dto.SubscriptionPlanResponse;
import com.ayush.subscription.subscription.dto.UpdatePlanRequest;

import java.util.List;
import java.util.UUID;

public interface SubscriptionPlanService {

    SubscriptionPlanResponse createPlan(CreatePlanRequest request);


    SubscriptionPlanResponse getPlanByUuid(UUID planUuid);

    List<SubscriptionPlanResponse> getAllPlans(int page, int size);

    SubscriptionPlanResponse updatePlan(UUID planUuid,
                                        UpdatePlanRequest request);

    Boolean deletePlan(UUID planUuid);

}
