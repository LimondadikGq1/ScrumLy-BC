package com.example.demo.auth.http.application;

import com.example.demo.auth.http.presentation.dto.requests.SignInRequest;
import com.example.demo.auth.http.presentation.dto.requests.SignUpRequest;
import com.example.demo.auth.http.presentation.dto.responces.JwtAuthTokenResponse;

public interface AuthService {

    JwtAuthTokenResponse register(SignUpRequest signUpRequest);

    JwtAuthTokenResponse login(SignInRequest signInRequest);

    JwtAuthTokenResponse refresh(String refreshToken);

    void signOut(String refreshToken);
}
