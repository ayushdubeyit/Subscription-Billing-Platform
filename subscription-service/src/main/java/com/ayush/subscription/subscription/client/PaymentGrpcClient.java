package com.ayush.subscription.subscription.client;


import com.ayush.subscription.payment.grpc.ProcessPaymentRequest;
import com.ayush.subscription.payment.grpc.ProcessPaymentResponse;
import io.grpc.ManagedChannel;
import org.springframework.stereotype.Component;
import com.ayush.subscription.payment.grpc.PaymentGrpcServiceGrpc;
import io.grpc.ManagedChannel;
import org.springframework.stereotype.Component;

@Component
public class PaymentGrpcClient {

    private final PaymentGrpcServiceGrpc.PaymentGrpcServiceBlockingStub blockingStub;

    public PaymentGrpcClient(ManagedChannel paymentChannel) {
        this.blockingStub = PaymentGrpcServiceGrpc.newBlockingStub(paymentChannel);
    }

    public ProcessPaymentResponse processPayment(
            String customerUuid,
            String subscriptionUuid,
            double amount,
            String currency
    ) {

        ProcessPaymentRequest request =
                ProcessPaymentRequest.newBuilder()
                        .setCustomerUuid(customerUuid)
                        .setSubscriptionUuid(subscriptionUuid)
                        .setAmount(amount)
                        .setCurrency(currency)
                        .build();

        return blockingStub.processPayment(request);

    }
}
