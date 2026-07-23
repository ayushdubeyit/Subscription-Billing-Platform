package com.ayush.subscription.gateway.resolver;

import com.ayush.subscription.gateway.client.CustomerServiceClient;
import com.ayush.subscription.gateway.dto.customer.CreateCustomerInput;
import com.ayush.subscription.gateway.dto.customer.CustomerDto;
import com.ayush.subscription.gateway.dto.customer.UpdateCustomerInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerMutationResolver {

    private final CustomerServiceClient customerServiceClient;

    public CustomerMutationResolver(CustomerServiceClient customerServiceClient) {
        this.customerServiceClient = customerServiceClient;
    }

    @MutationMapping
    public CustomerDto createCustomer(
            @Argument CreateCustomerInput input
    ) {

        return customerServiceClient.createCustomer(input);

    }
    @MutationMapping
    public CustomerDto updateCustomer(
            @Argument String customerUuid,
            @Argument UpdateCustomerInput input
    ) {
        return customerServiceClient.updateCustomer(customerUuid, input);
    }

    @MutationMapping
    public Boolean deleteCustomer(@Argument String customerUuid) {
        return customerServiceClient.deleteCustomer(customerUuid);
    }

}
