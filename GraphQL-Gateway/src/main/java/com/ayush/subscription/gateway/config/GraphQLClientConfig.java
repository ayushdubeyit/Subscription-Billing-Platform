package com.ayush.subscription.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQLClientConfig {

    @Bean("customerGraphQlClient")
    public HttpGraphQlClient customerGraphQlClient(WebClient.Builder builder) {

        WebClient client = builder
                .baseUrl("http://CUSTOMER-SERVICE/graphql")
                .build();

        return HttpGraphQlClient.builder(client).build();
    }



    @Bean("subscriptionGraphQlClient")
    public HttpGraphQlClient subscriptionGraphQlClient(WebClient.Builder builder) {

        WebClient client = builder
                .baseUrl("http://SUBSCRIPTION-SERVICE/graphql")
                .build();

        return HttpGraphQlClient.builder(client).build();
    }
    @Bean("billingGraphQlClient")
    public HttpGraphQlClient billingGraphQlClient(WebClient.Builder builder) {

        WebClient client = builder
                .baseUrl("http://BILLING-SERVICE/graphql")
                .build();

        return HttpGraphQlClient.builder(client).build();
    }

}
