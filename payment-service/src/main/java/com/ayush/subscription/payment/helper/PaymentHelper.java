package com.ayush.subscription.payment.helper;


import com.ayush.subscription.payment.dto.request.CreatePaymentRequest;
import com.ayush.subscription.payment.dto.response.PaymentResponse;
import com.ayush.subscription.payment.entity.Payment;
import com.ayush.subscription.payment.enums.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentHelper {

    public Payment toEntity(CreatePaymentRequest request) {
        Payment payment = new Payment();

        payment.setSubscriptionUuid(request.getSubscriptionUuid());
        payment.setCustomerUuid(request.getCustomerUuid());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setPaymentMethod(request.getPaymentMethod());


        payment.setStatus(PaymentStatus.SUCCESS);

        return payment;
    }

    public PaymentResponse toResponse(Payment payment) {

        PaymentResponse response = new PaymentResponse();

        response.setPaymentUuid(payment.getPaymentUuid());
        response.setSubscriptionUuid(payment.getSubscriptionUuid());
        response.setCustomerUuid(payment.getCustomerUuid());
        response.setAmount(payment.getAmount());
        response.setCurrency(payment.getCurrency());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setStatus(payment.getStatus());
        response.setTransactionId(payment.getTransactionId());
        response.setCreatedAt(payment.getCreatedAt());
        response.setUpdatedAt(payment.getUpdatedAt());

        return response;
    }
}