package com.ayush.subscription.gateway.client;


import com.ayush.subscription.gateway.dto.customer.CreateCustomerInput;
import com.ayush.subscription.gateway.dto.customer.CustomerDto;
import com.ayush.subscription.gateway.dto.customer.UpdateCustomerInput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceClient {
    private final HttpGraphQlClient graphQlClient;

    public CustomerServiceClient(
            @Qualifier("customerGraphQlClient")
            HttpGraphQlClient graphQlClient) {

        this.graphQlClient = graphQlClient;
    }

    public CustomerDto getCustomerByUuid(String customerUuid) {

        String document = """
                query($customerUuid: ID!) {
                
                    customerByUuid(customerUuid: $customerUuid){
                
                        customerUuid
                        firstName
                        lastName
                        email
                        phone
                        status
                
                    }
                
                }
                """;

        return graphQlClient
                .document(document)
                .variable("customerUuid", customerUuid)
                .retrieve("customerByUuid")
                .toEntity(CustomerDto.class)
                .block();

    }

    public CustomerDto createCustomer(CreateCustomerInput input) {

        String document = """
                mutation($input: CreateCustomerInput!) {
                
                    createCustomer(input: $input) {
                
                        customerUuid
                        firstName
                        lastName
                        email
                        phone
                        status
                
                    }
                
                }
                """;
        return graphQlClient
                .document(document)
                .variable("input", input)
                .retrieve("createCustomer")
                .toEntity(CustomerDto.class)
                .block();
    }

    public CustomerDto updateCustomer(String customerUuid,
                                      UpdateCustomerInput input) {

        String document = """
            mutation($customerUuid: ID!, $input: UpdateCustomerInput!) {

                updateCustomer(
                    customerUuid: $customerUuid,
                    input: $input
                ) {

                    customerUuid
                    firstName
                    lastName
                    email
                    phone
                    status

                }

            }
            """;

        return graphQlClient
                .document(document)
                .variable("customerUuid", customerUuid)
                .variable("input", input)
                .retrieve("updateCustomer")
                .toEntity(CustomerDto.class)
                .block();

    }

    public Boolean deleteCustomer(String customerUuid) {

        String document = """
                mutation($customerUuid: ID!) {
                
                    deleteCustomer(customerUuid: $customerUuid)
                
                }
                """;
        return graphQlClient
                .document(document)
                .variable("customerUuid", customerUuid)
                .retrieve("deleteCustomer")
                .toEntity(Boolean.class)
                .block();
    }
}
