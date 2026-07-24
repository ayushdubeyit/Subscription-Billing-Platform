package com.ayush.subscription.payment.producer;

import com.ayush.subscription.common.constants.KafkaTopics;
import com.ayush.subscription.common.event.PaymentFailedEvent;
import com.ayush.subscription.common.event.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public void publishPaymentSuccessEvent(
            PaymentSuccessEvent event) {


        log.info("Publishing event : {}", event);

        kafkaTemplate.send(KafkaTopics.PAYMENT_SUCCESS,
                        event.getPaymentUuid().toString(), event)
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

    public void publishPaymentFailedEvent(
            PaymentFailedEvent event) {


        log.info("Publishing event : {}", event);

        kafkaTemplate.send(KafkaTopics.PAYMENT_FAILED,
                        event.getPaymentUuid().toString(), event)
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
