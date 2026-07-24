package com.ayush.subscription.gateway.config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(authorizationHeaderForwardingFilter());
    }

    private ExchangeFilterFunction authorizationHeaderForwardingFilter() {
        return (request, next) -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes)
                    RequestContextHolder.getRequestAttributes();

            if (attributes == null) {
                return next.exchange(request);
            }

            HttpServletRequest servletRequest = attributes.getRequest();
            String authorizationHeader = servletRequest
                    .getHeader(HttpHeaders.AUTHORIZATION);

            if (authorizationHeader == null) {
                return next.exchange(request);
            }

            ClientRequest forwardedRequest = ClientRequest.from(request)
                    .headers(headers -> headers.set(
                            HttpHeaders.AUTHORIZATION,
                            authorizationHeader))
                    .build();

            return next.exchange(forwardedRequest);
        };
    }
}
