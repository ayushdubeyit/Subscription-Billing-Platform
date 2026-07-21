package com.ayush.subscription.payment.service;

import com.ayush.subscription.payment.dto.request.CreatePaymentRequest;
import com.ayush.subscription.payment.dto.request.UpdatePaymentStatusRequest;
import com.ayush.subscription.payment.dto.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    PaymentResponse createPayment(CreatePaymentRequest request);

    PaymentResponse getPaymentByUuid(UUID paymentUuid);

    List<PaymentResponse> getPaymentsByCustomer(UUID customerUuid);

    List<PaymentResponse> getPaymentsBySubscription(UUID subscriptionUuid);

    PaymentResponse updatePaymentStatus(UUID paymentUuid,
                                        UpdatePaymentStatusRequest request);

    void deletePayment(UUID paymentUuid);
}
