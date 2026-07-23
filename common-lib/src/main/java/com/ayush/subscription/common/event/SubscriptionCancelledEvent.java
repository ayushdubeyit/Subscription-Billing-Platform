package com.ayush.subscription.common.event;

import com.ayush.subscription.common.enums.EventType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class SubscriptionCancelledEvent extends BaseEvent{

    private final UUID subscriptionUuid;
    private final UUID customerUuid;
    private final UUID planUuid;
    private final String reason;

    public SubscriptionCancelledEvent(
            UUID subscriptionUuid,
            UUID customerUuid,
            UUID planUuid,
            String reason) {

        super(EventType.SUBSCRIPTION_CANCELLED);

        this.subscriptionUuid = subscriptionUuid;
        this.customerUuid = customerUuid;
        this.planUuid = planUuid;
        this.reason = reason;
    }
}
