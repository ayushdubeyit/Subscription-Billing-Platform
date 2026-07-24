package com.ayush.subscription.payment.service.impl;

import com.ayush.subscription.common.event.PaymentSuccessEvent;
import com.ayush.subscription.common.event.PaymentFailedEvent;
import com.ayush.subscription.payment.dto.request.CreatePaymentRequest;
import com.ayush.subscription.payment.dto.request.UpdatePaymentStatusRequest;
import com.ayush.subscription.payment.dto.response.PaymentResponse;
import com.ayush.subscription.payment.entity.Payment;
import com.ayush.subscription.payment.enums.PaymentStatus;
import com.ayush.subscription.payment.exception.InvalidPaymentStateException;
import com.ayush.subscription.payment.exception.PaymentNotFoundException;
import com.ayush.subscription.payment.helper.PaymentHelper;
import com.ayush.subscription.payment.producer.PaymentEventProducer;
import com.ayush.subscription.payment.repository.PaymentRepository;
import com.ayush.subscription.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final PaymentHelper paymentHelper;
    private final PaymentEventProducer paymentEventProducer;
    @Override
    public PaymentResponse createPayment(CreatePaymentRequest request) {
        Payment payment = paymentHelper.toEntity(request);
         payment.setTransactionId(UUID.randomUUID().toString());

         Payment savedPayment = repository.save(payment);

         publishPaymentEvent(savedPayment);

         return paymentHelper.toResponse(savedPayment);
    }

    @Override
    public PaymentResponse getPaymentByUuid(UUID paymentUuid) {
        Payment payment = repository.findByPaymentUuid(paymentUuid)
                .orElseThrow(()-> new PaymentNotFoundException
                        ("Payment Not Found With this UUID: " + paymentUuid));

        return paymentHelper.toResponse(payment);
    }

    @Override
    public List<PaymentResponse> getPaymentsByCustomer(UUID customerUuid) {
        return repository.findByCustomerUuid(customerUuid)
                .stream()
                .map(paymentHelper::toResponse)
                .toList();
    }

    @Override
    public List<PaymentResponse> getPaymentsBySubscription(UUID subscriptionUuid) {
        return repository.findBySubscriptionUuid(subscriptionUuid)
                .stream()
                .map(paymentHelper::toResponse)
                .toList();
    }

    @Override
    public PaymentResponse updatePaymentStatus(UUID paymentUuid, UpdatePaymentStatusRequest request) {
        Payment payment = repository.findByPaymentUuid(paymentUuid)
                .orElseThrow(() -> new PaymentNotFoundException(
                        "Payment not found with UUID: " + paymentUuid));

        if (payment.getStatus() == PaymentStatus.SUCCESS ||
                payment.getStatus() == PaymentStatus.FAILED) {

            throw new InvalidPaymentStateException(
                    "Payment status cannot be changed once it is " + payment.getStatus());
        }

        payment.setStatus(request.getStatus());

        Payment updatedPayment = repository.save(payment);

        publishPaymentEvent(updatedPayment);

        return paymentHelper.toResponse(updatedPayment);
    }

    @Override
    public void deletePayment(UUID paymentUuid) {
        Payment payment = repository.findByPaymentUuid(paymentUuid)
                .orElseThrow(() -> new PaymentNotFoundException(
                        "Payment not found with UUID: " + paymentUuid
                ));

        repository.delete(payment);
    }

    private void publishPaymentEvent(Payment payment) {
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            PaymentSuccessEvent event = new PaymentSuccessEvent(
                    payment.getPaymentUuid(),
                    payment.getSubscriptionUuid(),
                    payment.getCustomerUuid(),
                    payment.getAmount(),
                    payment.getCurrency()
            );

            paymentEventProducer.publishPaymentSuccessEvent(event);
        }

        if (payment.getStatus() == PaymentStatus.FAILED) {
            PaymentFailedEvent event = new PaymentFailedEvent(
                    payment.getPaymentUuid(),
                    payment.getSubscriptionUuid(),
                    payment.getCustomerUuid(),
                    payment.getAmount(),
                    payment.getStatus().name()
            );

            paymentEventProducer.publishPaymentFailedEvent(event);
        }
    }
}
