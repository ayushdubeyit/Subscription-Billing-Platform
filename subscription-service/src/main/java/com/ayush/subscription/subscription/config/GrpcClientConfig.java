package com.ayush.subscription.subscription.config;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @Value("${PAYMENT_GRPC_HOST:payment-service}")
    private String host;

    @Value("${PAYMENT_GRPC_PORT:9090}")
    private int port;

    @Bean
    public ManagedChannel paymentChannel() {


        System.out.println("======================================");
        System.out.println("Connecting to gRPC -> " + host + ":" + port);
        System.out.println("======================================");


        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
    }

}
