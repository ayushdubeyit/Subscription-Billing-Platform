package com.ayush.subscription.customer.graphql.query;


import com.ayush.subscription.customer.dto.response.CustomerResponse;
import com.ayush.subscription.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class CustomerQuery {
    private final CustomerService customerService;



    @QueryMapping
    public CustomerResponse customerByUuid(
            @Argument UUID customerUuid
            ){
        System.out.println("QUERY HIT");
        return customerService.getCustomerByUuid(customerUuid);
    }

    @QueryMapping
    public List<CustomerResponse> customers(
            @Argument int page,
            @Argument int size
    ){

        return customerService.getAllCustomers(page,size);

    }

}
