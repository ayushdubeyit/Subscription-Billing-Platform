package com.ayush.subscription.customer.util;


import com.ayush.subscription.customer.dto.request.CreateCustomerRequest;
import com.ayush.subscription.customer.dto.request.UpdateCustomerRequest;
import com.ayush.subscription.customer.dto.response.CustomerResponse;
import com.ayush.subscription.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerHelper {

    public Customer toEntity(CreateCustomerRequest request){

        Customer customer = new Customer();

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        return customer;
    }

    public CustomerResponse toResponse(Customer customer){

        return CustomerResponse.builder()
                .customerUuid(customer.getCustomerUuid())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .status(customer.getStatus().name())
                .build();
    }
    public void update(Customer customer,
                       UpdateCustomerRequest request){

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
    }
}
