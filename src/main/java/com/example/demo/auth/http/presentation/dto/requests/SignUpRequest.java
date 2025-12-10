package com.example.demo.auth.http.presentation.dto.requests;

import com.example.demo.global.validation.user.EmailValidation;
import com.example.demo.global.validation.user.PasswordValidation;
import com.example.demo.global.validation.user.UsernameValidation;
import lombok.Builder;

@Builder
public record SignUpRequest(

        @UsernameValidation
        String username,

        @EmailValidation
        String email,

        @PasswordValidation
        String password
) {
}
