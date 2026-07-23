package com.ayush.subscription.subscription.dto.customer;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerGraphQLResponse {

    private CustomerData data;

    @Getter
    @Setter
    public static class CustomerData {

        private CustomerResponse customerByUuid;

    }
}
