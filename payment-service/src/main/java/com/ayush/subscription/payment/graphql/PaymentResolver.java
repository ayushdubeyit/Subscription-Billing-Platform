package com.ayush.subscription.payment.graphql;


import com.ayush.subscription.payment.dto.request.CreatePaymentRequest;
import com.ayush.subscription.payment.dto.request.UpdatePaymentStatusRequest;
import com.ayush.subscription.payment.dto.response.PaymentResponse;
import com.ayush.subscription.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PaymentResolver {

       private final PaymentService paymentService;

    @QueryMapping
    public PaymentResponse getPayment(
            @Argument UUID paymentUuid
    ) {
        return paymentService.getPaymentByUuid(paymentUuid);
    }
    @QueryMapping
    public List<PaymentResponse> getPaymentsByCustomer(
            @Argument UUID customerUuid
    ) {

        return paymentService.getPaymentsByCustomer(customerUuid);
    }
    @MutationMapping
    public PaymentResponse createPayment(
            @Argument CreatePaymentRequest input
    ) {
        return paymentService.createPayment(input);
    }

    @MutationMapping
    public PaymentResponse updatePaymentStatus(
            @Argument UUID paymentUuid,
            @Argument UpdatePaymentStatusRequest input
    ) {
        return paymentService.updatePaymentStatus(paymentUuid, input);
    }

    @MutationMapping
    public Boolean deletePayment(
            @Argument UUID paymentUuid
    ) {

        paymentService.deletePayment(paymentUuid);

        return true;
    }
}
