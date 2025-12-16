package com.example.demo.auth.http.presentation.web;

import com.example.demo.auth.http.application.AuthService;
import com.example.demo.auth.http.presentation.dto.requests.SignInRequest;
import com.example.demo.auth.http.presentation.dto.requests.SignUpRequest;
import com.example.demo.auth.http.presentation.dto.responces.JwtAuthTokenResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.example.demo.auth.http.presentation.web.AuthPaths.AUTH_BASE_PATH;
import static com.example.demo.auth.http.presentation.web.AuthPaths.LOGIN_PATH;
import static com.example.demo.auth.http.presentation.web.AuthPaths.LOGOUT_PATH;
import static com.example.demo.auth.http.presentation.web.AuthPaths.REFRESH_PATH;
import static com.example.demo.auth.http.presentation.web.AuthPaths.REGISTER_PATH;
import static com.example.demo.auth.http.presentation.web.AuthPaths.V1;

@Slf4j
@RestController
@RequestMapping(path = AUTH_BASE_PATH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = V1 + REGISTER_PATH)
    public ResponseEntity<JwtAuthTokenResponse> register(
            HttpServletResponse response,
            @RequestBody @Valid SignUpRequest signUpRequest
    ) {
        JwtAuthTokenResponse jwt = authService.register(signUpRequest);
        Cookie cookie = authService.createCookie(jwt.refreshToken());
        response.addCookie(cookie);
        response.setContentType("application/json");
        return ResponseEntity.created(URI.create(AUTH_BASE_PATH + V1 + REGISTER_PATH)).body(jwt);
    }

    @PostMapping(path = V1 + LOGIN_PATH)
    public ResponseEntity<JwtAuthTokenResponse> login(
            HttpServletResponse response,
            @RequestBody @Valid SignInRequest signInRequest
    ) {
        JwtAuthTokenResponse jwt = authService.login(signInRequest);
        Cookie cookie = authService.createCookie(jwt.refreshToken());
        response.addCookie(cookie);
        response.setContentType("application/json");
        return ResponseEntity.ok(jwt);
    }

    @PostMapping(path = V1 + REFRESH_PATH)
    public ResponseEntity<JwtAuthTokenResponse> refresh(
            @CookieValue(value = "refresh") String token
    ) {
        JwtAuthTokenResponse authResponse = authService.refresh(token);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping(path = V1 + LOGOUT_PATH)
    public ResponseEntity<Void> logout(
            HttpServletResponse response,
            @CookieValue(value = "refresh") String token
    ) {
        Cookie removedCookie = authService.signOut(token);
        response.addCookie(removedCookie);
        return ResponseEntity.noContent().build();
    }
}
