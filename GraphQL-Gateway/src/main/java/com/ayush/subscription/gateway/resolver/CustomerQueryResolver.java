package com.ayush.subscription.gateway.resolver;


import com.ayush.subscription.gateway.client.CustomerServiceClient;
import com.ayush.subscription.gateway.dto.customer.CustomerDto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerQueryResolver {

    private final CustomerServiceClient customerServiceClient;

    public CustomerQueryResolver(CustomerServiceClient customerServiceClient) {
        this.customerServiceClient = customerServiceClient;
    }

    @QueryMapping
    public CustomerDto customerByUuid(@Argument String customerUuid) {

        return customerServiceClient.getCustomerByUuid(customerUuid);

    }
}
