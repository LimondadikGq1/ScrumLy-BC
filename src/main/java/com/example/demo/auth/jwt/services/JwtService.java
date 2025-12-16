package com.example.demo.auth.jwt.services;

import com.example.demo.auth.configurations.AccessJwtConfigurationProperties;
import com.example.demo.auth.configurations.RefreshJwtConfigurationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final AccessJwtConfigurationProperties accessJwtConfigurationProperties;

    private final RefreshJwtConfigurationProperties refreshJwtConfigurationProperties;

    public String generateAccessToken(String email) {
        return generateToken(email, accessJwtConfigurationProperties.getDuration());
    }

    public String generateRefreshToken(String email) {
        return generateToken(email, refreshJwtConfigurationProperties.getDuration());
    }

    public String generateToken(String email, Duration expireTime) {
        Instant now = Instant.now();
        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(expireTime)))
                .signWith(getSecretKey())
                .compact();
    }

    public Claims getPayload(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = getPayload(token);
        return resolver.apply(claims);
    }

    public String getEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date getExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenAlive(String token) {
        return getExpiration(token).after(new Date());
    }

    public SecretKey getSecretKey() {
        byte[] bytes = Decoders.BASE64.decode(accessJwtConfigurationProperties.getSecret());
        return Keys.hmacShaKeyFor(bytes);
    }
}
