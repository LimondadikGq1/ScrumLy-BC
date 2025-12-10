package com.example.demo.auth.http.presentation.dto.responces;


import lombok.Builder;

@Builder
public record JwtAuthTokenResponse(

        String accessToken,

        String refreshToken
) {
}
