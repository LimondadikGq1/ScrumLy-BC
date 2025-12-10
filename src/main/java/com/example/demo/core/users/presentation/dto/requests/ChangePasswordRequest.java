package com.example.demo.core.users.presentation.dto.requests;

import com.example.demo.global.validation.user.PasswordValidation;
import lombok.Builder;


@Builder
public record ChangePasswordRequest(

        @PasswordValidation
        String newPassword
) {
}
