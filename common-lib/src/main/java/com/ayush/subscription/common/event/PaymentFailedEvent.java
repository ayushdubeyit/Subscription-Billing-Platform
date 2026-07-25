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
public class PaymentFailedEvent extends BaseEvent{


    private UUID paymentUuid;
    private UUID subscriptionUuid;
    private UUID customerUuid;
    private BigDecimal amount;
    private String reason;

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
