package com.ayush.subscription.gateway.resolver;

import com.ayush.subscription.gateway.client.BillingServiceClient;
import com.ayush.subscription.gateway.dto.billing.BillingDto;
import com.ayush.subscription.gateway.dto.billing.InvoiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BillingQueryResolver {

    private final BillingServiceClient billingServiceClient;

    @QueryMapping
    public BillingDto getBilling(
            @Argument String billingUuid
    ) {
        return billingServiceClient.getBilling(billingUuid);
    }
    @QueryMapping
    public InvoiceDto getInvoice(
            @Argument String invoiceUuid
    ) {
        return billingServiceClient.getInvoice(invoiceUuid);
    }
    @QueryMapping
    public List<BillingDto> getBillingsByCustomer(
            @Argument String customerUuid
    ) {
        return billingServiceClient.getBillingsByCustomer(customerUuid);
    }
    @QueryMapping
    public List<BillingDto> getBillingsBySubscription(
            @Argument String subscriptionUuid
    ) {
        return billingServiceClient.getBillingsBySubscription(subscriptionUuid);
    }
}
