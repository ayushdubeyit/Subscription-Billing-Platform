package com.ayush.subscription.subscription.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class GraphqlClientConfig {
    @Value("${billing.service.url}")
    private String billingServiceUrl;

    @Bean
    public HttpGraphQlClient billingGraphQlClient() {

        WebClient webClient = WebClient.builder()
                .baseUrl(billingServiceUrl)
                .build();

        return HttpGraphQlClient.builder(webClient).build();
    }

}