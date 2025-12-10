package com.example.demo.auth.jwt.services;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtVerifier {

    private final JwtService jwtService;

    public String getTokenOrNull(String fullToken) {
        if (fullToken != null && fullToken.startsWith("Bearer ")) {
            log.debug("Token not null - first stage");
            return fullToken.split(" ")[1].trim();
        }
        log.debug("Token get failed on first stage: {}", fullToken);
        return null;
    }

    public boolean validateToken(String jwt) {
        try {
            jwtService.getPayload(jwt);
            return jwtService.isTokenAlive(jwt);
        } catch (
                MalformedJwtException |
                UnsupportedJwtException |
                ExpiredJwtException e
        ) {
            log.debug("Error token validate on second stage");
            return false;
        }
    }
}
