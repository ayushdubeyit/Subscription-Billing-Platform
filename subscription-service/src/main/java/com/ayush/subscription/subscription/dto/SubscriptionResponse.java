package com.ayush.subscription.subscription.dto;

import com.ayush.subscription.subscription.enums.SubscriptionStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionResponse {
    private UUID subscriptionUuid;

    private UUID customerUuid;

    private UUID planUuid;

    private SubscriptionStatus status;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate nextBillingDate;

    private Boolean autoRenew;
}
