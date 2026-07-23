package com.ayush.subscription.subscription.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class GraphqlClientConfig {
    @Value("${billing.service.url}")
    private String billingServiceUrl;

    @Value("${customer.service.url}")
    private String customerServiceUrl;

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean("billingGraphQlClient")
    public HttpGraphQlClient billingGraphQlClient(WebClient.Builder builder) {

        WebClient webClient = builder
                .baseUrl(billingServiceUrl)
                .build();

        return HttpGraphQlClient.builder(webClient).build();
    }

    @Bean("customerGraphQlClient")
    public HttpGraphQlClient customerGraphQlClient(WebClient.Builder builder) {

        WebClient webClient = builder
                .baseUrl(customerServiceUrl)
                .build();

        return HttpGraphQlClient.builder(webClient).build();
    }

}