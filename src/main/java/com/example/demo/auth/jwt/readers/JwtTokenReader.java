package com.example.demo.auth.jwt.readers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface JwtTokenReader {

    UsernamePasswordAuthenticationToken extractUser(String fullToken);
}
