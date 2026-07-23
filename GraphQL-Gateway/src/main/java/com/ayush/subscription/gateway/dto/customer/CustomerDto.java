package com.ayush.subscription.gateway.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String customerUuid;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String status;
}
