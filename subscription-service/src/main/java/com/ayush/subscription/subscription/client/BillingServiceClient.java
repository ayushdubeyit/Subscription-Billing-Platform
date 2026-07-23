package com.ayush.subscription.subscription.client;

import com.ayush.subscription.subscription.dto.billing.BillingResponse;
import com.ayush.subscription.subscription.dto.billing.CreateBillingRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillingServiceClient {

    @Qualifier("billingGraphQlClient")
    private final HttpGraphQlClient billingGraphQlClient;

    private static final String CREATE_BILLING_MUTATION = """
            mutation CreateBilling($request: CreateBillingRequest!) {
                createBilling(request: $request) {
                    billingUuid
                    customerUuid
                    subscriptionUuid
                    totalAmount
                    currency
                    status
                    createdAt
                }
            }
            """;

    public BillingResponse createBilling(CreateBillingRequest request) {

        return billingGraphQlClient
                .document(CREATE_BILLING_MUTATION)
                .variable("request", request)
                .retrieve("createBilling")
                .toEntity(BillingResponse.class)
                .block();

    }
}