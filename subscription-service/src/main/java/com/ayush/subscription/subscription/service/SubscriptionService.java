package com.ayush.subscription.subscription.service;

import com.ayush.subscription.subscription.dto.CreateSubscriptionRequest;
import com.ayush.subscription.subscription.dto.SubscriptionResponse;
import com.ayush.subscription.subscription.dto.UpdateSubscriptionRequest;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    SubscriptionResponse createSubscription(
            CreateSubscriptionRequest request);

    SubscriptionResponse getSubscriptionByUuid(
            UUID subscriptionUuid);

    List<SubscriptionResponse> getSubscriptionsByCustomer(
            UUID customerUuid);

    SubscriptionResponse updateSubscription(
            UUID subscriptionUuid,
            UpdateSubscriptionRequest request);

    Boolean cancelSubscription(
            UUID subscriptionUuid);
}
