package com.ayush.subscription.subscription.client;

import com.ayush.subscription.subscription.dto.customer.CustomerResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerServiceClient {

    @Qualifier("customerGraphQlClient")
    private final HttpGraphQlClient customerGraphQlClient;
    private final HttpServletRequest httpServletRequest;

    private static final String CUSTOMER_BY_UUID_QUERY = """
        query CustomerByUuid($customerUuid: ID!) {
            customerByUuid(customerUuid: $customerUuid) {
                customerUuid
                firstName
                lastName
                email
                phone
                status
            }
        }
        """;
    public CustomerResponse getCustomerByUuid(UUID customerUuid) {

        return customerGraphQlClientWithAuthorization()
                .document(CUSTOMER_BY_UUID_QUERY)
                .variable("customerUuid", customerUuid.toString())
                .retrieve("customerByUuid")
                .toEntity(CustomerResponse.class)
                .block();
    }

    private HttpGraphQlClient customerGraphQlClientWithAuthorization() {
        String authorizationHeader = httpServletRequest
                .getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null) {
            return customerGraphQlClient;
        }

        return customerGraphQlClient.mutate()
                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                .build();
    }

}
