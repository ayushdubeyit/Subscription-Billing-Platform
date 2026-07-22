package com.ayush.subscription.subscription.service.impl;

import com.ayush.subscription.subscription.dto.CreatePlanRequest;
import com.ayush.subscription.subscription.dto.SubscriptionPlanResponse;
import com.ayush.subscription.subscription.dto.UpdatePlanRequest;

import com.ayush.subscription.subscription.entity.SubscriptionPlan;
import com.ayush.subscription.subscription.enums.PlanStatus;
import com.ayush.subscription.subscription.exception.DuplicatePlanException;
import com.ayush.subscription.subscription.exception.PlanNotFoundException;
import com.ayush.subscription.subscription.repository.SubscriptionPlanRepository;
import com.ayush.subscription.subscription.service.SubscriptionPlanService;
import com.ayush.subscription.subscription.util.PlanHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository repository;
    @Override
    @Transactional
    public SubscriptionPlanResponse createPlan(CreatePlanRequest request) {

        if (repository.existsByName(request.getName())) {
            throw new DuplicatePlanException(request.getName());
        }

        SubscriptionPlan plan = SubscriptionPlan.builder()
                .planUuid(UUID.randomUUID())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .billingCycle(request.getBillingCycle())
                .status(PlanStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();



        return PlanHelper.toResponse(plan);
    }



    @Cacheable(value = "subscriptionPlans", key = "#planUuid")
    @Override
    public SubscriptionPlanResponse getPlanByUuid(UUID planUuid) {

        System.out.println("DATABASE HIT HAPPEN!!!!!");

        SubscriptionPlan plan = repository.findByPlanUuid(planUuid)
                .orElseThrow(() -> new PlanNotFoundException(planUuid));

        return PlanHelper.toResponse(plan);
    }

    @Override
    public List<SubscriptionPlanResponse> getAllPlans(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(pageable)
                .stream()
                .map(PlanHelper::toResponse)
                .toList();
    }
    @CachePut(value = "subscriptionPlans", key = "#planUuid")
    @Override
    @Transactional
    public SubscriptionPlanResponse updatePlan(UUID planUuid, UpdatePlanRequest request) {

        SubscriptionPlan plan = repository.findByPlanUuid(planUuid)
                .orElseThrow(() -> new PlanNotFoundException(planUuid));

        plan.setName(request.getName());
        plan.setDescription(request.getDescription());
        plan.setPrice(request.getPrice());
        plan.setBillingCycle(request.getBillingCycle());
        plan.setStatus(request.getStatus());

        plan.setUpdatedAt(LocalDateTime.now());

        return PlanHelper.toResponse(repository.save(plan));
    }


    @CacheEvict(value = "subscriptionPlans", key = "#planUuid")
    @Override
    public Boolean deletePlan(UUID planUuid) {
        SubscriptionPlan plan = repository.findByPlanUuid(planUuid)
                .orElseThrow(() -> new PlanNotFoundException(planUuid));

        repository.delete(plan);

        return true;
    }
}
