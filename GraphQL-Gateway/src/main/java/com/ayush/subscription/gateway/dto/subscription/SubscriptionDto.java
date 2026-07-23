package com.ayush.subscription.gateway.dto.subscription;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionDto {
    private String subscriptionUuid;

    private String customerUuid;

    private String planUuid;

    private SubscriptionStatus status;

    private String startDate;

    private String endDate;

    private String nextBillingDate;

    private Boolean autoRenew;
}
