package com.ayush.subscription.subscription.service;


import com.ayush.subscription.common.event.SubscriptionCreatedEvent;
import com.ayush.subscription.payment.grpc.ProcessPaymentResponse;
import com.ayush.subscription.subscription.client.BillingServiceClient;
import com.ayush.subscription.subscription.client.PaymentGrpcClient;
import com.ayush.subscription.subscription.dto.billing.BillingResponse;
import com.ayush.subscription.subscription.dto.billing.CreateBillingRequest;
import com.ayush.subscription.subscription.entity.Subscription;
import com.ayush.subscription.subscription.entity.SubscriptionPlan;
import com.ayush.subscription.subscription.enums.SubscriptionStatus;
import com.ayush.subscription.subscription.exception.PlanNotFoundException;
import com.ayush.subscription.subscription.producer.SubscriptionEventProducer;
import com.ayush.subscription.subscription.repository.SubscriptionPlanRepository;
import com.ayush.subscription.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionRenewalService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionPlanRepository planRepository;
    private final PaymentGrpcClient paymentGrpcClient;
    private final BillingServiceClient billingServiceClient;
    private final SubscriptionEventProducer subscriptionEventProducer;

    public void processRenewals() {

        List<Subscription> subscriptions =
                subscriptionRepository.findByStatusAndAutoRenewTrueAndNextBillingDateLessThanEqual(
                        SubscriptionStatus.ACTIVE,
                        LocalDate.now()
                );

        log.info("Found {} subscriptions for renewal.", subscriptions.size());

        for (Subscription subscription : subscriptions) {

            log.info("Processing renewal for subscription {}",
                    subscription.getSubscriptionUuid());

            SubscriptionPlan plan = planRepository
                    .findByPlanUuid(subscription.getPlanUuid())
                    .orElseThrow(() ->
                            new PlanNotFoundException(subscription.getPlanUuid()));

            LocalDate nextBillingDate;

            switch (plan.getBillingCycle()) {

                case MONTHLY ->
                        nextBillingDate = subscription.getNextBillingDate().plusMonths(1);

                case QUARTERLY ->
                        nextBillingDate = subscription.getNextBillingDate().plusMonths(3);

                case YEARLY ->
                        nextBillingDate = subscription.getNextBillingDate().plusYears(1);

                default ->
                        throw new IllegalStateException("Invalid Billing Cycle");
            }
            ProcessPaymentResponse paymentResponse =
                    paymentGrpcClient.processPayment(
                            subscription.getCustomerUuid().toString(),
                            subscription.getSubscriptionUuid().toString(),
                            plan.getPrice().doubleValue(),
                            "INR"
                    );

            if (!"SUCCESS".equalsIgnoreCase(paymentResponse.getStatus())) {
                log.error("Renewal payment failed for subscription {}",
                        subscription.getSubscriptionUuid());

                continue;
            }
            subscription.setNextBillingDate(nextBillingDate);
            subscription.setEndDate(nextBillingDate);
            subscription.setUpdatedAt(LocalDateTime.now());

            subscriptionRepository.save(subscription);


            CreateBillingRequest billingRequest =
                    CreateBillingRequest.builder()
                            .customerUuid(subscription.getCustomerUuid())
                            .subscriptionUuid(subscription.getSubscriptionUuid())
                            .baseAmount(plan.getPrice())
                            .discount(BigDecimal.ZERO)
                            .tax(BigDecimal.ZERO)
                            .currency("INR")
                            .build();

            BillingResponse billingResponse =
                    billingServiceClient.createBilling(billingRequest);

            if (billingResponse == null) {
                throw new RuntimeException("Billing Service returned null response.");
            }
            SubscriptionCreatedEvent event =
                    new SubscriptionCreatedEvent(
                            subscription.getSubscriptionUuid(),
                            subscription.getCustomerUuid(),
                            subscription.getPlanUuid(),
                            subscription.getStatus().name()
                    );

            subscriptionEventProducer.publishSubscriptionCreatedEvent(event);
        }

    }
}
