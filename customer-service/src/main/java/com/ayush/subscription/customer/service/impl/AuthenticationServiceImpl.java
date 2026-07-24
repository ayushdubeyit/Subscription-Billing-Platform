package com.ayush.subscription.customer.service.impl;

import com.ayush.subscription.customer.dto.request.LoginRequest;
import com.ayush.subscription.customer.dto.request.RegisterRequest;
import com.ayush.subscription.customer.dto.response.AuthResponse;
import com.ayush.subscription.customer.entity.Customer;
import com.ayush.subscription.customer.enums.CustomerStatus;
import com.ayush.subscription.customer.enums.Role;
import com.ayush.subscription.customer.exception.DuplicateEmailException;
import com.ayush.subscription.customer.repository.CustomerRepository;
import com.ayush.subscription.customer.service.AuthenticationService;
import com.ayush.subscription.customer.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(request.getEmail());
        }

        Customer customer = Customer.builder()
                .customerUuid(UUID.randomUUID())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .status(CustomerStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return AuthResponse.builder()
                .accessToken(null)
                .customerUuid(savedCustomer.getCustomerUuid())
                .email(savedCustomer.getEmail())
                .role(savedCustomer.getRole())
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Customer not found with email: " + request.getEmail()));

        if (customer.getPassword() == null
                || !passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return AuthResponse.builder()
                .accessToken(jwtUtil.generateToken(customer))
                .customerUuid(customer.getCustomerUuid())
                .email(customer.getEmail())
                .role(customer.getRole())
                .build();
    }
}
