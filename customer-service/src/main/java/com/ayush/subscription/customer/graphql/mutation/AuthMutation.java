package com.ayush.subscription.customer.graphql.mutation;

import com.ayush.subscription.customer.dto.request.LoginRequest;
import com.ayush.subscription.customer.dto.request.RegisterRequest;
import com.ayush.subscription.customer.dto.response.AuthResponse;
import com.ayush.subscription.customer.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthMutation {

    private final AuthenticationService authenticationService;

    @MutationMapping
    public AuthResponse register(@Argument @Valid RegisterRequest input) {
        return authenticationService.register(input);
    }

    @MutationMapping
    public AuthResponse login(@Argument @Valid  LoginRequest input) {
        return authenticationService.login(input);
    }
}
