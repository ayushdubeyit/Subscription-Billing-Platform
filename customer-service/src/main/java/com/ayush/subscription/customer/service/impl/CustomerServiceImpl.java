package com.ayush.subscription.customer.service.impl;

import com.ayush.subscription.customer.dto.request.CreateCustomerRequest;
import com.ayush.subscription.customer.dto.request.UpdateCustomerRequest;
import com.ayush.subscription.customer.dto.response.CustomerResponse;
import com.ayush.subscription.customer.entity.Customer;
import com.ayush.subscription.customer.enums.CustomerStatus;
import com.ayush.subscription.customer.exception.CustomerNotFoundException;
import com.ayush.subscription.customer.exception.DuplicateEmailException;

import com.ayush.subscription.customer.repository.CustomerRepository;
import com.ayush.subscription.customer.service.CustomerService;
import com.ayush.subscription.customer.util.CacheConstants;
import com.ayush.subscription.customer.util.CustomerHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerHelper customerHelper;


    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        if(repository.existsByEmail(request.getEmail())){
            throw new DuplicateEmailException(request.getEmail());
        }

        Customer customer = customerHelper.toEntity(request);

            customer.setCustomerUuid(UUID.randomUUID());
            customer.setCreatedAt(LocalDateTime.now());
            customer.setStatus(CustomerStatus.ACTIVE);
            customer.setUpdatedAt(LocalDateTime.now());

            Customer savedCustomer = repository.save(customer);
            return customerHelper.toResponse(savedCustomer);
    }

    @Cacheable(
            value = CacheConstants.CUSTOMER_CACHE,
            key = "#customerUuid"
    )
    @Override
    public CustomerResponse getCustomerByUuid(UUID customerUuid) {
        System.out.println("METHOD EXECUTED");
        Customer customer = repository.findByCustomerUuid(customerUuid)
                .orElseThrow(()-> new CustomerNotFoundException(customerUuid));
        CustomerResponse response = customerHelper.toResponse(customer);
        System.out.println(response.getFirstName());
        System.out.println(response.getLastName());
        System.out.println(response.getEmail());

        return response;
    }

    @Override
    public List<CustomerResponse> getAllCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable)
                .stream()
                .map(customerHelper::toResponse)
                .toList();
    }

    @CachePut(
            value = CacheConstants.CUSTOMER_CACHE,
            key = "#customerUuid"
    )
    @Override
    @Transactional
    public CustomerResponse updateCustomer(UUID customerUuid,
                                           UpdateCustomerRequest request) {

        Customer customer = repository.findByCustomerUuid(customerUuid)
                .orElseThrow(() ->
                        new CustomerNotFoundException(customerUuid));


        if (!customer.getEmail().equals(request.getEmail())
                && repository.existsByEmail(request.getEmail())) {

            throw new RuntimeException("Email Already Exists");
        }


        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        customer.setUpdatedAt(LocalDateTime.now());

        Customer updatedCustomer = repository.save(customer);

        return customerHelper.toResponse(updatedCustomer);
    }


    @CacheEvict(
            value = CacheConstants.CUSTOMER_CACHE,
            key = "#customerUuid"
    )
    @Override
    @Transactional
    public Boolean deleteCustomer(UUID customerUuid) {

        Customer customer = repository
                .findByCustomerUuidAndStatus(
                        customerUuid,
                        CustomerStatus.ACTIVE
                )
                .orElseThrow(() ->
                        new CustomerNotFoundException(customerUuid));

        customer.setStatus(CustomerStatus.DELETED);
        customer.setUpdatedAt(LocalDateTime.now());

        repository.save(customer);

        return true;
    }

}
