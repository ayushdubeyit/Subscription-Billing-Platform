package com.ayush.subscription.customer.repository;

import com.ayush.subscription.customer.entity.Customer;
import com.ayush.subscription.customer.enums.CustomerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface CustomerRepository extends JpaRepository<Customer ,Long> {
    Optional<Customer> findByCustomerUuid(UUID customerUuid);
    Optional<Customer> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Customer> findByCustomerUuidAndStatus(
            UUID customerUuid,
            CustomerStatus status
    );
    Page<Customer> findAllByStatus(
            CustomerStatus status,
            Pageable pageable
    );



}

