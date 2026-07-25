package com.ayush.subscription.common.event;

import com.ayush.subscription.common.enums.EventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class PaymentSuccessEvent extends BaseEvent{

    private UUID paymentUuid;
    private UUID subscriptionUuid;
    private UUID customerUuid;
    private BigDecimal amount;
    private String currency;

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
