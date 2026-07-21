package com.ayush.subscription.payment.grpc;

import com.ayush.subscription.payment.dto.request.CreatePaymentRequest;
import com.ayush.subscription.payment.dto.response.PaymentResponse;
import com.ayush.subscription.payment.enums.PaymentMethod;
import com.ayush.subscription.payment.service.PaymentService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ayush.subscription.payment.grpc.PaymentGrpcServiceGrpc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentGrpcServiceImpl extends  PaymentGrpcServiceGrpc.PaymentGrpcServiceImplBase {
    private final PaymentService paymentService;

    @Override
    public void processPayment(
            ProcessPaymentRequest request,
            StreamObserver<ProcessPaymentResponse> responseObserver
    ) {

        CreatePaymentRequest paymentRequest = new CreatePaymentRequest();

        paymentRequest.setCustomerUuid(
                UUID.fromString(request.getCustomerUuid()));

        paymentRequest.setSubscriptionUuid(
                UUID.fromString(request.getSubscriptionUuid()));

            paymentRequest.setAmount(BigDecimal.valueOf(request.getAmount()));

            paymentRequest.setCurrency(request.getCurrency());

            paymentRequest.setPaymentMethod(PaymentMethod.CARD);

            PaymentResponse paymentResponse =
                    paymentService.createPayment(paymentRequest);

            ProcessPaymentResponse response =
                    ProcessPaymentResponse.newBuilder()
                            .setPaymentUuid(paymentResponse.getPaymentUuid().toString())
                            .setTransactionId(paymentResponse.getTransactionId())
                            .setStatus(paymentResponse.getStatus().name())
                            .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        }

        @Override
        public void getPaymentHistory(
                GetPaymentHistoryRequest request,
                StreamObserver<GetPaymentHistoryResponse> responseObserver
        ) {
            List<PaymentResponse> payments =
                    paymentService.getPaymentsByCustomer(
                            UUID.fromString(request.getCustomerUuid()));
            GetPaymentHistoryResponse.Builder responseBuilder =
                    GetPaymentHistoryResponse.newBuilder();

            for (PaymentResponse payment : payments) {

                PaymentHistory history =
                        PaymentHistory.newBuilder()
                                .setPaymentUuid(payment.getPaymentUuid().toString())
                                .setAmount(payment.getAmount().doubleValue())
                                .setStatus(payment.getStatus().name())
                                .setTransactionId(payment.getTransactionId())
                                .build();

                responseBuilder.addPayments(history);


            }
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();

        }
    }
