package com.ayush.subscription.notification.consumer;

import com.ayush.subscription.common.constants.KafkaTopics;
import com.ayush.subscription.common.event.PaymentSuccessEvent;
import com.ayush.subscription.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSuccessConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = KafkaTopics.PAYMENT_SUCCESS,
            containerFactory = "paymentSuccessKafkaListenerContainerFactory"
    )
    public void consume(PaymentSuccessEvent event) {
        notificationService.handlePaymentSuccess(event);
    }
}
