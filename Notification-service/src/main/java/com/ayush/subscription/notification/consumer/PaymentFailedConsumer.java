package com.ayush.subscription.notification.consumer;

import com.ayush.subscription.common.constants.KafkaTopics;
import com.ayush.subscription.common.event.PaymentFailedEvent;
import com.ayush.subscription.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFailedConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = KafkaTopics.PAYMENT_FAILED,
            containerFactory = "paymentFailedKafkaListenerContainerFactory"
    )
    public void consume(PaymentFailedEvent event) {
        notificationService.handlePaymentFailed(event);
    }
}
