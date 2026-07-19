package com.ayush.subscription.subscription.repository;

import com.ayush.subscription.subscription.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionPlanRepository extends JpaRepository< SubscriptionPlan , Long> {

    Optional<SubscriptionPlan> findByPlanUuid(UUID planUuid);

    boolean existsByName(String name);

}
