package com.ayush.subscription.common.event;

import com.ayush.subscription.common.enums.EventType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class PaymentFailedEvent extends BaseEvent{


    private final UUID paymentUuid;
    private final UUID subscriptionUuid;
    private final UUID customerUuid;
    private final BigDecimal amount;
    private final String reason;

    public PaymentFailedEvent(
            UUID paymentUuid,
            UUID subscriptionUuid,
            UUID customerUuid,
            BigDecimal amount,
            String reason) {

        super(EventType.PAYMENT_FAILED);

        this.paymentUuid = paymentUuid;
        this.subscriptionUuid = subscriptionUuid;
        this.customerUuid = customerUuid;
        this.amount = amount;
        this.reason = reason;
    }
}
