package com.ayush.subscription.notification.consumer;

import com.ayush.subscription.common.constants.KafkaTopics;
import com.ayush.subscription.common.event.InvoiceGeneratedEvent;
import com.ayush.subscription.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceGeneratedConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = KafkaTopics.INVOICE_GENERATED,
            containerFactory = "invoiceGeneratedKafkaListenerContainerFactory"
    )
    public void consume(InvoiceGeneratedEvent event) {
        notificationService.handleInvoiceGenerated(event);
    }
}
