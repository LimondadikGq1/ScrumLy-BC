package com.example.demo.core.users.presentation.dto.requests;

import com.example.demo.global.validation.user.PasswordValidation;
import com.example.demo.global.validation.user.UsernameValidation;
import lombok.Builder;

@Builder
public record ChangeProfileRequest(

        @UsernameValidation
        String username,

        @PasswordValidation
        String password
) {
}
