package com.ayush.subscription.subscription.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

import io.grpc.ManagedChannel;

@Configuration
public class GrpcClientConfig {

    @Bean
    public ManagedChannel paymentChannel() {
        return io.grpc.ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();
    }

}
