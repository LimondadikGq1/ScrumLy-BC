package com.example.demo.auth.jwt.readers.impl;

import com.example.demo.auth.jwt.readers.JwtTokenReader;
import com.example.demo.auth.jwt.services.JwtService;
import com.example.demo.auth.jwt.services.JwtVerifier;
import com.example.demo.auth.user.UserDetails;
import com.example.demo.auth.user.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.example.demo.auth.jwt.constants.ReaderTypes.HTTP_READER;

@Slf4j
@Service
@RequiredArgsConstructor
@Qualifier(HTTP_READER)
public class HttpJwtTokenReader implements JwtTokenReader {

    private final JwtVerifier jwtVerifier;

    private final JwtService jwtService;

    private final UserDetailsServiceImpl userDetailsService;

    private String extractAndValidateToken(String fullToken) {
        String jwt = jwtVerifier.getTokenOrNull(fullToken);

        if (jwt == null || !jwtVerifier.validateToken(jwt)) {
            return null;
        }
        return jwt;
    }

    @Override
    public UsernamePasswordAuthenticationToken extractUser(String fullToken) {

        String jwt = extractAndValidateToken(fullToken);

        if (jwt == null) {
            log.info("Failed to extract jwt: null");
            return null;
        }

        String email = jwtService.getEmail(jwt);

        if (email == null) {
            log.info("Failed to get email from jwt: null");
            return null;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        log.debug("Success get user: {}", userDetails.getId());

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}
