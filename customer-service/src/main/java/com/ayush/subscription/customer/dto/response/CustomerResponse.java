package com.ayush.subscription.customer.dto.response;


import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse{



    private UUID customerUuid;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String status;
}
