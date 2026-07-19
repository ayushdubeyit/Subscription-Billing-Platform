package com.ayush.subscription.subscription.repository;

import com.ayush.subscription.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    Optional<Subscription> findBySubscriptionUuid(UUID subscriptionUuid);

    List<Subscription> findByCustomerUuid(UUID customerUuid);

    List<Subscription> findByPlanUuid(UUID planUuid);

    boolean existsByCustomerUuidAndPlanUuid(
            UUID customerUuid,
            UUID planUuid
    );
}
