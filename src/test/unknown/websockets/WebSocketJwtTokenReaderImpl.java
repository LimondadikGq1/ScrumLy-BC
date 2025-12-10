package com.example.demo.unknown.websockets;

import com.example.demo.auth.jwt.services.JwtService;
import com.example.demo.auth.jwt.services.JwtVerifier;
import com.example.demo.auth.jwt.readers.JwtTokenReader;
import com.example.demo.auth.configurations.http.UserDetails;
import com.example.demo.auth.configurations.http.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("websockets-reader")
public class WebSocketJwtTokenReaderImpl implements JwtTokenReader {

    private final JwtVerifier jwtVerifier;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public String extractAndValidateToken(String jwt) {
        String token  = jwtVerifier.getTokenOrNull(jwt);
        if(token == null || !jwtVerifier.validateToken(jwt)){
            return null;
        }
        return token;

    }
    @Override
    public UsernamePasswordAuthenticationToken extractUser(String jwt) {
        String token = extractAndValidateToken(jwt);
        if(token == null){
            return null;
        }
        String email = jwtService.getEmail(token);
        if(email == null || SecurityContextHolder.getContext().getAuthentication() != null){
            return null;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken user =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
        return user;
    }
}
