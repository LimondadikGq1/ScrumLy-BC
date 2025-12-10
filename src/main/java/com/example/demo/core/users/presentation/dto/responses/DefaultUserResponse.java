package com.example.demo.core.users.presentation.dto.responses;

import lombok.Builder;

@Builder
public record DefaultUserResponse(

        Long id,

        String username,

        String email
) {
}
