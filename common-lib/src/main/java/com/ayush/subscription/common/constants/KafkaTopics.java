package com.ayush.subscription.common.constants;

public final class KafkaTopics {

    public static final String SUBSCRIPTION_CREATED = "subscription.created";
    public static final String SUBSCRIPTION_RENEWED = "subscription.renewed";
    public static final String SUBSCRIPTION_CANCELLED = "subscription.cancelled";

    public static final String PAYMENT_SUCCESS = "payment.success";
    public static final String PAYMENT_FAILED = "payment.failed";

    public static final String INVOICE_GENERATED = "invoice.generated";

    private KafkaTopics(){}
}
