package com.ayush.subscription.customer.service;

import com.ayush.subscription.customer.dto.request.LoginRequest;
import com.ayush.subscription.customer.dto.request.RegisterRequest;
import com.ayush.subscription.customer.dto.response.AuthResponse;

public interface AuthenticationService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
