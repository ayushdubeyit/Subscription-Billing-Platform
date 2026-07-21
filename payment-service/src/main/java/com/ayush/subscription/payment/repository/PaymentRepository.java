package com.ayush.subscription.payment.repository;

import com.ayush.subscription.payment.entity.Payment;
import com.ayush.subscription.payment.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment , Long> {


    Optional<Payment> findByPaymentUuid(UUID paymentUuid);
    List<Payment> findByCustomerUuid(UUID customerUuid);

    List<Payment> findBySubscriptionUuid(UUID subscriptionUuid);

    List<Payment> findByStatus(PaymentStatus status);

    boolean existsByPaymentUuid(UUID paymentUuid);
}
