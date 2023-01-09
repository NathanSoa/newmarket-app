package com.activity.newmarketapp.domain.service;

import com.activity.newmarketapp.config.security.utils.JwtTokenUtils;
import com.activity.newmarketapp.presentation.dtos.login.LoginRequest;
import com.activity.newmarketapp.presentation.dtos.login.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        String jwtToken = jwtTokenUtils.generateToken(request.username());
        return new LoginResponse(request.username(), jwtToken);
    }
}
