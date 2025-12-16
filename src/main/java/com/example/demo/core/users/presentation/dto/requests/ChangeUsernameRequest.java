package com.example.demo.core.users.presentation.dto.requests;

import com.example.demo.global.validation.user.UsernameValidation;
import lombok.Builder;

@Builder
public record ChangeUsernameRequest(

        @UsernameValidation
        String username
) {
}
