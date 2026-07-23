package com.ayush.subscription.gateway.client;

import com.ayush.subscription.gateway.dto.billing.BillingDto;
import com.ayush.subscription.gateway.dto.billing.InvoiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BillingServiceClient {

    @Qualifier("billingGraphQlClient")
    private final HttpGraphQlClient graphQlClient;

    public BillingServiceClient(
            @Qualifier("billingGraphQlClient")
            HttpGraphQlClient graphQlClient
    ) {
        this.graphQlClient = graphQlClient;
    }

    public BillingDto getBilling(String billingUuid) {

        String document = """
                query GetBilling($billingUuid: ID!) {
                    getBilling(billingUuid: $billingUuid) {
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
        System.out.println("Calling Billing Service...");

        BillingDto response = graphQlClient.document(document)
                .variable("billingUuid", billingUuid)
                .retrieve("getBilling")
                .toEntity(BillingDto.class)
                .block();

        System.out.println("Response = " + response);

        return response;
    }
    public InvoiceDto getInvoice(String invoiceUuid) {

        String document = """
                query GetInvoice($invoiceUuid: ID!) {
                    getInvoice(invoiceUuid: $invoiceUuid) {
                        invoiceUuid
                        invoiceNumber
                        amount
                        status
                        issuedAt
                    }
                }
                """;
        return graphQlClient.document(document)
                .variable("invoiceUuid", invoiceUuid)
                .retrieve("getInvoice")
                .toEntity(InvoiceDto.class)
                .block();
    }
    public List<BillingDto> getBillingsByCustomer(String customerUuid) {

        String document = """
                query GetBillingsByCustomer($customerUuid: ID!) {
                    getBillingsByCustomer(customerUuid: $customerUuid) {
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

        return graphQlClient.document(document)
                .variable("customerUuid", customerUuid)
                .retrieve("getBillingsByCustomer")
                .toEntityList(BillingDto.class)
                .block();
    }
    public List<BillingDto> getBillingsBySubscription(String subscriptionUuid) {

        String document = """
                query GetBillingsBySubscription($subscriptionUuid: ID!) {
                    getBillingsBySubscription(subscriptionUuid: $subscriptionUuid) {
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
        return graphQlClient.document(document)
                .variable("subscriptionUuid", subscriptionUuid)
                .retrieve("getBillingsBySubscription")
                .toEntityList(BillingDto.class)
                .block();
    }
}
