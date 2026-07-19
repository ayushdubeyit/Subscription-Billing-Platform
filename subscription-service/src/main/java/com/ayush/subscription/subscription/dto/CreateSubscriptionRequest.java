package com.ayush.subscription.subscription.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSubscriptionRequest {
    private UUID customerUuid;

    private UUID planUuid;

    private Boolean autoRenew;
}
