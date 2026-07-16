package com.ayush.subscription.customer.service
        ;

import com.ayush.subscription.customer.dto.request.CreateCustomerRequest;
import com.ayush.subscription.customer.dto.request.UpdateCustomerRequest;
import com.ayush.subscription.customer.dto.response.CustomerResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerResponse createCustomer(CreateCustomerRequest request);

    CustomerResponse getCustomerByUuid(UUID customerUuid);

    List<CustomerResponse> getAllCustomers(
            int page,
            int size
    );

    CustomerResponse updateCustomer(
            UUID customerUuid,
            UpdateCustomerRequest request
    );
    Boolean deleteCustomer(UUID customerUuid);

}
