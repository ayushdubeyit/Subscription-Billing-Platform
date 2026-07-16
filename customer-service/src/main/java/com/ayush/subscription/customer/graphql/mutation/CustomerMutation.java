package com.ayush.subscription.customer.graphql.mutation;

import com.ayush.subscription.customer.dto.request.CreateCustomerRequest;
import com.ayush.subscription.customer.dto.request.UpdateCustomerRequest;
import com.ayush.subscription.customer.dto.response.CustomerResponse;
import com.ayush.subscription.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class CustomerMutation {

    private final CustomerService customerService;



    @MutationMapping
    public CustomerResponse createCustomer(@Argument("input") CreateCustomerRequest request){
        return customerService.createCustomer(request);
    }


    @MutationMapping
    public CustomerResponse updateCustomer(@Argument("customerUuid") UUID customerByUuid ,
                                           @Argument("input") UpdateCustomerRequest request){
        return  customerService.updateCustomer(customerByUuid,request);
    }

    @MutationMapping
    public Boolean deleteCustomer(
            @Argument UUID customerUuid
    ) {
        return customerService.deleteCustomer(customerUuid);
    }

}
