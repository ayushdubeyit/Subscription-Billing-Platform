package com.ayush.subscription.gateway.dto.subscription;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSubscriptionInput {
    private String customerUuid;

    private String planUuid;

    private Boolean autoRenew;

}
