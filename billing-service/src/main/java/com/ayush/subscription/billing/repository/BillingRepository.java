package com.ayush.subscription.billing.repository;

import com.ayush.subscription.billing.entity.Billing;
import com.ayush.subscription.billing.enums.BillingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BillingRepository extends JpaRepository<Billing ,Long> {


    Optional<Billing> findByBillingUuid(UUID billingUuid);

    List<Billing> findByCustomerUuid(UUID customerUuid);

    List<Billing> findBySubscriptionUuid(UUID subscriptionUuid);

    List<Billing> findByStatus(BillingStatus status);

    boolean existsByBillingUuid(UUID billingUuid);
}
