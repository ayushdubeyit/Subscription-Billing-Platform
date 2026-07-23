package com.ayush.subscription.common.event;

import com.ayush.subscription.common.enums.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class SubscriptionRenewedEvent extends BaseEvent{
    private final UUID subscriptionUuid;
    private final UUID customerUuid;
    private final UUID planUuid;
    private final String status;

    public SubscriptionRenewedEvent(
            UUID subscriptionUuid,
            UUID customerUuid,
            UUID planUuid,
            String status) {

        super(EventType.SUBSCRIPTION_RENEWED);

        this.subscriptionUuid = subscriptionUuid;
        this.customerUuid = customerUuid;
        this.planUuid = planUuid;
        this.status = status;
    }


}
