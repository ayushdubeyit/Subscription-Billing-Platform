package com.ayush.subscription.billing.graphql;


import com.ayush.subscription.billing.dto.response.BillingResponse;
import com.ayush.subscription.billing.service.interfaces.BillingService;
import lombok.RequiredArgsConstructor;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BillingQuery {

    private final BillingService billingService;
    @QueryMapping
    public String health() {
        return "Billing Service Running";
    }

    @QueryMapping
    public BillingResponse getBilling(
            @Argument UUID billingUuid
    ) {
        return billingService.getBillingByUuid(billingUuid);
    }

}
