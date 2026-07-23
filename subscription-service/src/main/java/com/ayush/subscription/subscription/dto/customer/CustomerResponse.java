package com.ayush.subscription.subscription.dto.customer;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private UUID customerUuid;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String status;
}
