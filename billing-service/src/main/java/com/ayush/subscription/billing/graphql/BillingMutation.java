package com.ayush.subscription.billing.graphql;

import com.ayush.subscription.billing.dto.request.CreateBillingRequest;
import com.ayush.subscription.billing.dto.response.BillingResponse;
import com.ayush.subscription.billing.service.interfaces.BillingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class BillingMutation {

    private final BillingService billingService;



    @MutationMapping
    public BillingResponse createBilling(
            @Argument  @Valid  CreateBillingRequest request
    ) {

        return billingService.createBilling(request);
    }
}
