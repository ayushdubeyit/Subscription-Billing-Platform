package com.ayush.subscription.subscription.service.impl;

import com.ayush.subscription.subscription.dto.CreateSubscriptionRequest;
import com.ayush.subscription.subscription.dto.SubscriptionResponse;
import com.ayush.subscription.subscription.dto.UpdateSubscriptionRequest;
import com.ayush.subscription.subscription.entity.Subscription;
import com.ayush.subscription.subscription.entity.SubscriptionPlan;
import com.ayush.subscription.subscription.enums.SubscriptionStatus;
import com.ayush.subscription.subscription.exception.PlanNotFoundException;
import com.ayush.subscription.subscription.exception.SubscriptionNotFoundException;
import com.ayush.subscription.subscription.repository.SubscriptionPlanRepository;
import com.ayush.subscription.subscription.repository.SubscriptionRepository;
import com.ayush.subscription.subscription.service.SubscriptionService;
import com.ayush.subscription.subscription.util.SubscriptionHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionPlanRepository planRepository;
    private final SubscriptionRepository repository;

    @Override
    @Transactional
    public SubscriptionResponse createSubscription(CreateSubscriptionRequest request) {
        SubscriptionPlan plan = planRepository
                .findByPlanUuid(request.getPlanUuid())
                .orElseThrow(() ->
                        new PlanNotFoundException(request.getPlanUuid()));

        LocalDate startDate = LocalDate.now();

        LocalDate nextBillingDate;

        switch (plan.getBillingCycle()) {

            case MONTHLY ->
                    nextBillingDate = startDate.plusMonths(1);

            case QUARTERLY ->
                    nextBillingDate = startDate.plusMonths(3);

            case YEARLY ->
                    nextBillingDate = startDate.plusYears(1);

            default ->
                    throw new IllegalStateException("Invalid Billing Cycle");
        }

        Subscription subscription = Subscription.builder()

                .subscriptionUuid(UUID.randomUUID())
                .customerUuid(request.getCustomerUuid())
                .planUuid(request.getPlanUuid())
                .status(SubscriptionStatus.ACTIVE)
                .startDate(startDate)
                .endDate(nextBillingDate)
                .nextBillingDate(nextBillingDate)
                .autoRenew(request.getAutoRenew())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return SubscriptionHelper.toResponse(repository.save(subscription));

    }
    @Cacheable(
            value = "subscriptions",
            key = "#subscriptionUuid"
    )
    @Override
    public SubscriptionResponse getSubscriptionByUuid(UUID subscriptionUuid) {
        Subscription subscription = repository
                .findBySubscriptionUuid(subscriptionUuid)
                .orElseThrow(() ->
                        new SubscriptionNotFoundException(subscriptionUuid));

        return SubscriptionHelper.toResponse(subscription);
    }

    @Override
    public List<SubscriptionResponse> getSubscriptionsByCustomer(UUID customerUuid) {
        return repository
                .findByCustomerUuid(customerUuid)
                .stream()
                .map(SubscriptionHelper::toResponse)
                .toList();
    }

    @CachePut(
            value = "subscriptions",
            key = "#subscriptionUuid"
    )
    @Override
    public SubscriptionResponse updateSubscription(UUID subscriptionUuid, UpdateSubscriptionRequest request) {
        Subscription subscription = repository
                .findBySubscriptionUuid(subscriptionUuid)
                .orElseThrow(() ->
                        new SubscriptionNotFoundException(subscriptionUuid));

        SubscriptionPlan plan = planRepository
                .findByPlanUuid(request.getPlanUuid())
                .orElseThrow(() ->
                        new PlanNotFoundException(request.getPlanUuid()));

        subscription.setPlanUuid(plan.getPlanUuid());

        subscription.setStatus(request.getStatus());

        subscription.setAutoRenew(request.getAutoRenew());

        subscription.setUpdatedAt(LocalDateTime.now());

        return SubscriptionHelper.toResponse(repository.save(subscription));




    }


    @Transactional
    @CacheEvict(
            value = "subscriptions",
            key = "#subscriptionUuid"
    )

    @Override
    public Boolean cancelSubscription(UUID subscriptionUuid) {
        Subscription subscription = repository
                .findBySubscriptionUuid(subscriptionUuid)
                .orElseThrow(() ->
                        new SubscriptionNotFoundException(subscriptionUuid));

        repository.delete(subscription);

        return true;
    }
}
