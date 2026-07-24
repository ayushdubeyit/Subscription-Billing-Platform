package com.ayush.subscription.notification.service;

import com.ayush.subscription.common.event.InvoiceGeneratedEvent;
import com.ayush.subscription.common.event.PaymentFailedEvent;
import com.ayush.subscription.common.event.PaymentSuccessEvent;
import com.ayush.subscription.common.event.SubscriptionCreatedEvent;

public interface NotificationService {

    void handleSubscriptionCreated(SubscriptionCreatedEvent event);

    void handlePaymentSuccess(PaymentSuccessEvent event);

    void handlePaymentFailed(PaymentFailedEvent event);

    void handleInvoiceGenerated(InvoiceGeneratedEvent event);
}
