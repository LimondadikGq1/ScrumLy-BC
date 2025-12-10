package com.example.demo.auth.http.presentation.dto.requests;

import com.example.demo.global.validation.user.EmailValidation;
import com.example.demo.global.validation.user.PasswordValidation;
import lombok.Builder;

@Builder
public record SignInRequest(

        @EmailValidation
        String email,

        @PasswordValidation
        String password
) {
}
