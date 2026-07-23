package com.ayush.subscription.gateway.dto.subscription;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateSubscriptionInput {
    private String planUuid;

    private Boolean autoRenew;

    private SubscriptionStatus status;
}
