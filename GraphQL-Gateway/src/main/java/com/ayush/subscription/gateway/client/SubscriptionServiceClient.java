package com.ayush.subscription.gateway.client;

import com.ayush.subscription.gateway.dto.subscription.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubscriptionServiceClient {

    private final HttpGraphQlClient graphQlClient;

    public SubscriptionServiceClient(
            @Qualifier("subscriptionGraphQlClient")
            HttpGraphQlClient graphQlClient) {

        this.graphQlClient = graphQlClient;
    }
    public SubscriptionPlanDto getPlanByUuid(String planUuid) {

        String document = """
                query($planUuid: ID!) {
                
                    planByUuid(planUuid: $planUuid) {
                
                        planUuid
                        name
                        description
                        price
                        billingCycle
                        status
                
                    }
                
                }
                """;
        return graphQlClient
                .document(document)
                .variable("planUuid", planUuid)
                .retrieve("planByUuid")
                .toEntity(SubscriptionPlanDto.class)
                .block();
    }

        public List<SubscriptionPlanDto> getAllPlans(int page, int size) {

            String document = """
                    query($page: Int!, $size: Int!) {
                    
                        allPlans(page: $page, size: $size) {
                    
                            planUuid
                            name
                            description
                            price
                            billingCycle
                            status
                    
                        }
                    
                    }
                    """;
            return graphQlClient
                    .document(document)
                    .variable("page", page)
                    .variable("size", size)
                    .retrieve("allPlans")
                    .toEntityList(SubscriptionPlanDto.class)
                    .block();

        }
    public SubscriptionPlanDto createPlan(CreatePlanInput input) {

        String document = """
                mutation($input:CreatePlanInput!){
                    createPlan(input:$input){
                        planUuid
                        name
                        description
                        price
                        billingCycle
                        status
                    }
                }
                """;
        return graphQlClient
                .document(document)
                .variable("input", input)
                .retrieve("createPlan")
                .toEntity(SubscriptionPlanDto.class)
                .block();
    }
    public SubscriptionPlanDto updatePlan(String planUuid,
                                          UpdatePlanInput input) {

        String document = """
                mutation($planUuid:ID!,$input:UpdatePlanInput!){
                    updatePlan(
                        planUuid:$planUuid,
                        input:$input
                    ){
                        planUuid
                        name
                        description
                        price
                        billingCycle
                        status
                    }
                }
                """;
        return graphQlClient
                .document(document)
                .variable("planUuid", planUuid)
                .variable("input", input)
                .retrieve("updatePlan")
                .toEntity(SubscriptionPlanDto.class)
                .block();
    }
    public Boolean deletePlan(String planUuid){

        String document="""
                mutation($planUuid:ID!){
                    deletePlan(planUuid:$planUuid)
                }
                """;

        return graphQlClient
                .document(document)
                .variable("planUuid",planUuid)
                .retrieve("deletePlan")
                .toEntity(Boolean.class)
                .block();
    }

    public SubscriptionDto getSubscriptionByUuid(String subscriptionUuid) {

        String document = """
            query($subscriptionUuid: ID!) {

                subscriptionByUuid(subscriptionUuid: $subscriptionUuid) {

                    subscriptionUuid
                    customerUuid
                    planUuid
                    status
                    startDate
                    endDate
                    nextBillingDate
                    autoRenew

                }

            }
            """;
        return graphQlClient
                .document(document)
                .variable("subscriptionUuid", subscriptionUuid)
                .retrieve("subscriptionByUuid")
                .toEntity(SubscriptionDto.class)
                .block();
    }
    public List<SubscriptionDto> getSubscriptionsByCustomer(String customerUuid) {

        String document = """
                query($customerUuid: ID!) {
                
                    subscriptionsByCustomer(customerUuid: $customerUuid) {
                
                        subscriptionUuid
                        customerUuid
                        planUuid
                        status
                        startDate
                        endDate
                        nextBillingDate
                        autoRenew
                
                    }
                
                }
                """;
        return graphQlClient
                .document(document)
                .variable("customerUuid", customerUuid)
                .retrieve("subscriptionsByCustomer")
                .toEntityList(SubscriptionDto.class)
                .block();
    }
    public SubscriptionDto createSubscription(
            CreateSubscriptionInput input) {

        String document = """
            mutation($input: CreateSubscriptionInput!) {

                createSubscription(input: $input) {

                    subscriptionUuid
                    customerUuid
                    planUuid
                    status
                    startDate
                    endDate
                    nextBillingDate
                    autoRenew

                }

            }
            """;
        return graphQlClient
                .document(document)
                .variable("input", input)
                .retrieve("createSubscription")
                .toEntity(SubscriptionDto.class)
                .block();
    }
    public SubscriptionDto updateSubscription(
            String subscriptionUuid,
            UpdateSubscriptionInput input) {

        String document = """
            mutation(
                $subscriptionUuid: ID!,
                $input: UpdateSubscriptionInput!
            ) {

                updateSubscription(
                    subscriptionUuid: $subscriptionUuid,
                    input: $input
                ) {

                    subscriptionUuid
                    customerUuid
                    planUuid
                    status
                    startDate
                    endDate
                    nextBillingDate
                    autoRenew

                }

            }
            """;

        return graphQlClient
                .document(document)
                .variable("subscriptionUuid", subscriptionUuid)
                .variable("input", input)
                .retrieve("updateSubscription")
                .toEntity(SubscriptionDto.class)
                .block();
    }

    public Boolean cancelSubscription(String subscriptionUuid) {

        String document = """
                mutation($subscriptionUuid: ID!) {
                
                    cancelSubscription(
                        subscriptionUuid: $subscriptionUuid
                    )
                
                }
                """;

        return graphQlClient
                .document(document)
                .variable("subscriptionUuid", subscriptionUuid)
                .retrieve("cancelSubscription")
                .toEntity(Boolean.class)
                .block();
    }

}
