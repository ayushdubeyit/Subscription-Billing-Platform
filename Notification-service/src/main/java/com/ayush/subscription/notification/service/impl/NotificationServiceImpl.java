package com.ayush.subscription.notification.service.impl;

import com.ayush.subscription.common.event.InvoiceGeneratedEvent;
import com.ayush.subscription.common.event.PaymentFailedEvent;
import com.ayush.subscription.common.event.PaymentSuccessEvent;
import com.ayush.subscription.common.event.SubscriptionCreatedEvent;
import com.ayush.subscription.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void handleSubscriptionCreated(SubscriptionCreatedEvent event) {
        log.info("Notification: subscription created. SubscriptionUuid={}, CustomerUuid={}, Status={}",
                event.getSubscriptionUuid(), event.getCustomerUuid(), event.getStatus());
    }

    @Override
    public void handlePaymentSuccess(PaymentSuccessEvent event) {
        log.info("Notification: payment successful. PaymentUuid={}, CustomerUuid={}, Amount={} {}",
                event.getPaymentUuid(), event.getCustomerUuid(),
                event.getAmount(), event.getCurrency());
    }

    @Override
    public void handlePaymentFailed(PaymentFailedEvent event) {
        log.info("Notification: payment failed. PaymentUuid={}, CustomerUuid={}, Reason={}",
                event.getPaymentUuid(), event.getCustomerUuid(), event.getReason());
    }

    @Override
    public void handleInvoiceGenerated(InvoiceGeneratedEvent event) {
        log.info("Notification: invoice generated. InvoiceUuid={}, CustomerUuid={}, Amount={} {}",
                event.getInvoiceUuid(), event.getCustomerUuid(),
                event.getTotalAmount(), event.getCurrency());
    }
}
