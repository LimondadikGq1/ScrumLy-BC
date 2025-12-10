package com.example.demo.auth.http;

import com.example.demo.auth.jwt.readers.JwtTokenReader;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.demo.auth.jwt.constants.ReaderTypes.HTTP_READER;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtTokenReader jwtTokenReader;

    public JwtAuthFilter(@Qualifier(HTTP_READER) JwtTokenReader jwtTokenReader) {
        this.jwtTokenReader = jwtTokenReader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String fullToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        UsernamePasswordAuthenticationToken user = jwtTokenReader.extractUser(fullToken);

        if (user == null) {
            log.info("User after authentification: null - failed");
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(user);
        log.info("User success authentification");

        filterChain.doFilter(request, response);
    }
}
