package com.ayush.subscription.notification.config;

import com.ayush.subscription.common.event.InvoiceGeneratedEvent;
import com.ayush.subscription.common.event.PaymentFailedEvent;
import com.ayush.subscription.common.event.PaymentSuccessEvent;
import com.ayush.subscription.common.event.SubscriptionCreatedEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.util.UUID;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    private <T> ConsumerFactory<String, T> consumerFactory(Class<T> eventType) {
        JsonDeserializer<T> valueDeserializer =
                new JsonDeserializer<>(eventType, objectMapper(), false);
        valueDeserializer.addTrustedPackages(
                "com.ayush.subscription.common.event");

        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                valueDeserializer
        );
    }

    private ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.addMixIn(SubscriptionCreatedEvent.class,
                SubscriptionCreatedEventMixin.class);
        objectMapper.addMixIn(PaymentSuccessEvent.class,
                PaymentSuccessEventMixin.class);
        objectMapper.addMixIn(PaymentFailedEvent.class,
                PaymentFailedEventMixin.class);
        objectMapper.addMixIn(InvoiceGeneratedEvent.class,
                InvoiceGeneratedEventMixin.class);
        return objectMapper;
    }

    private <T> ConcurrentKafkaListenerContainerFactory<String, T>
    kafkaListenerContainerFactory(Class<T> eventType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(eventType));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SubscriptionCreatedEvent>
    subscriptionCreatedKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory(SubscriptionCreatedEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentSuccessEvent>
    paymentSuccessKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory(PaymentSuccessEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentFailedEvent>
    paymentFailedKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory(PaymentFailedEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, InvoiceGeneratedEvent>
    invoiceGeneratedKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory(InvoiceGeneratedEvent.class);
    }

    private abstract static class SubscriptionCreatedEventMixin {

        @JsonCreator
        SubscriptionCreatedEventMixin(
                @JsonProperty("subscriptionUuid") UUID subscriptionUuid,
                @JsonProperty("customerUuid") UUID customerUuid,
                @JsonProperty("planUuid") UUID planUuid,
                @JsonProperty("status") String status) {
        }
    }

    private abstract static class PaymentSuccessEventMixin {

        @JsonCreator
        PaymentSuccessEventMixin(
                @JsonProperty("paymentUuid") UUID paymentUuid,
                @JsonProperty("subscriptionUuid") UUID subscriptionUuid,
                @JsonProperty("customerUuid") UUID customerUuid,
                @JsonProperty("amount") BigDecimal amount,
                @JsonProperty("currency") String currency) {
        }
    }

    private abstract static class PaymentFailedEventMixin {

        @JsonCreator
        PaymentFailedEventMixin(
                @JsonProperty("paymentUuid") UUID paymentUuid,
                @JsonProperty("subscriptionUuid") UUID subscriptionUuid,
                @JsonProperty("customerUuid") UUID customerUuid,
                @JsonProperty("amount") BigDecimal amount,
                @JsonProperty("reason") String reason) {
        }
    }

    private abstract static class InvoiceGeneratedEventMixin {

        @JsonCreator
        InvoiceGeneratedEventMixin(
                @JsonProperty("invoiceUuid") UUID invoiceUuid,
                @JsonProperty("billingUuid") UUID billingUuid,
                @JsonProperty("customerUuid") UUID customerUuid,
                @JsonProperty("totalAmount") BigDecimal totalAmount,
                @JsonProperty("currency") String currency) {
        }
    }
}
