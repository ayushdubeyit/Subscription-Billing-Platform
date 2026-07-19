package com.ayush.subscription.subscription.dto;

import com.ayush.subscription.subscription.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSubscriptionRequest {

    private UUID planUuid;

    private Boolean autoRenew;

    private SubscriptionStatus status;
}
