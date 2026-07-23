package com.ayush.subscription.common.event;

import com.ayush.subscription.common.enums.EventType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class SubscriptionCreatedEvent extends BaseEvent{

    private final UUID subscriptionUuid;
    private final UUID customerUuid;
    private final UUID planUuid;
    private final String status;

    public SubscriptionCreatedEvent(
            UUID subscriptionUuid,
            UUID customerUuid,
            UUID planUuid,
            String status) {

        super(EventType.SUBSCRIPTION_CREATED);

        this.subscriptionUuid = subscriptionUuid;
        this.customerUuid = customerUuid;
        this.planUuid = planUuid;
        this.status = status;
    }
}
