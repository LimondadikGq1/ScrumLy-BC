package com.example.demo.auth.http.application.impl;

import com.example.demo.auth.http.application.AuthService;
import com.example.demo.auth.http.exceptions.AuthentificationException;
import com.example.demo.auth.http.presentation.dto.requests.SignInRequest;
import com.example.demo.auth.http.presentation.dto.requests.SignUpRequest;
import com.example.demo.auth.http.presentation.dto.responces.JwtAuthTokenResponse;
import com.example.demo.auth.jwt.services.JwtService;
import com.example.demo.core.users.exceptions.SystemRoleNotFoundException;
import com.example.demo.core.users.exceptions.UserNotFoundException;
import com.example.demo.core.users.infrastructure.entity.SystemRole;
import com.example.demo.core.users.infrastructure.entity.User;
import com.example.demo.core.users.infrastructure.jpa.RoleRepository;
import com.example.demo.core.users.infrastructure.jpa.UserRepository;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.global.exceptions.constants.ExceptionKeys.AUTHENTIFICATION_EXCEPTION;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.SYSTEM_ROLE_NOT_FOUND_BY_ID;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.USER_NOT_FOUND_BY_EMAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    public static final String ROLE_USER = "USER";

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public JwtAuthTokenResponse register(SignUpRequest signUpRequest) {
        SystemRole role = roleRepository.findByName(ROLE_USER)
                .orElseThrow(() -> new SystemRoleNotFoundException(SYSTEM_ROLE_NOT_FOUND_BY_ID, ROLE_USER));
        log.info("Success get system role: {}", role);

        var user = User
                .builder()
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .username(signUpRequest.username())
                .role(role)
                .build();


        User savedUser = userRepository.save(user);
        log.info("Saved user id: {}", savedUser.getId());

        return generateAuthResponse(savedUser.getEmail());
    }

    @Override
    public JwtAuthTokenResponse login(SignInRequest signInRequest) {
        String email = signInRequest.email();
        log.info("User email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_EMAIL, email));
        log.info("User id: {}", user.getId());

        if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
            log.info("Failed to login - passwords doesn't match");
            throw new AuthentificationException(AUTHENTIFICATION_EXCEPTION,signInRequest.password());
        }

        return generateAuthResponse(user.getEmail());
    }

    @Override
    public JwtAuthTokenResponse refresh(String refresh) {
        if (jwtService.isTokenAlive(refresh)) {
            String email = jwtService.getEmail(refresh);
            log.info("Success refresh with email: {}", email);
            return generateAuthResponse(email);
        }
        log.info("Failed to refresh token");
        throw new AuthentificationException(AUTHENTIFICATION_EXCEPTION);
    }

    @Override
    public Cookie signOut(String refreshToken) {
        if (jwtService.isTokenAlive(refreshToken)) {
            Cookie cookieToRemove = new Cookie("refresh", null);
            cookieToRemove.setMaxAge(0);
            cookieToRemove.setPath("/");
            SecurityContextHolder.clearContext();
            log.info("Success sign out");
            return cookieToRemove;
        }
        throw new AuthentificationException("User not authenticated");
    }

    @Override
    public Cookie createCookie(String refresh) {
        Cookie cookie = new Cookie("refresh",refresh);
        cookie.setPath("/");
        cookie.setMaxAge(20000);
        return cookie;
    }

    private JwtAuthTokenResponse generateAuthResponse(String email) {
        return JwtAuthTokenResponse.builder()
                .accessToken(jwtService.generateAccessToken(email))
                .refreshToken(jwtService.generateRefreshToken(email))
                .build();
    }
}

