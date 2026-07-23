package com.ayush.subscription.common.event;

import com.ayush.subscription.common.enums.EventType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class PaymentSuccessEvent extends BaseEvent{

    private final UUID paymentUuid;
    private final UUID subscriptionUuid;
    private final UUID customerUuid;
    private final BigDecimal amount;
    private final String currency;

    public PaymentSuccessEvent(
            UUID paymentUuid,
            UUID subscriptionUuid,
            UUID customerUuid,
            BigDecimal amount,
            String currency) {

        super(EventType.PAYMENT_SUCCESS);

        this.paymentUuid = paymentUuid;
        this.subscriptionUuid = subscriptionUuid;
        this.customerUuid = customerUuid;
        this.amount = amount;
        this.currency = currency;
    }
    }

