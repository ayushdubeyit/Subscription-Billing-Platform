package com.ayush.subscription.notification.consumer;

import com.ayush.subscription.common.constants.KafkaTopics;
import com.ayush.subscription.common.event.SubscriptionCreatedEvent;
import com.ayush.subscription.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionCreatedConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = KafkaTopics.SUBSCRIPTION_CREATED,
            containerFactory = "subscriptionCreatedKafkaListenerContainerFactory"
    )
    public void consume(SubscriptionCreatedEvent event) {
        notificationService.handleSubscriptionCreated(event);
    }
}
