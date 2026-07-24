package com.ayush.subscription.billing.producer;

import com.ayush.subscription.common.constants.KafkaTopics;
import com.ayush.subscription.common.event.InvoiceGeneratedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillingEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public void publishInvoiceGeneratedEvent(
            InvoiceGeneratedEvent event) {


        log.info("Publishing event : {}", event);

        kafkaTemplate.send(KafkaTopics.INVOICE_GENERATED, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Kafka publish failed", ex);
                    } else {
                        log.info("Kafka publish success. Topic={}, Offset={}",
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().offset());
                    }
                });
        log.info("Kafka send invoked");
    }
}
