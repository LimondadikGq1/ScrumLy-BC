package com.example.demo.global.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.demo.global.MessageKeys.USER_PASSWORD_INCORRECT_SIZE;
import static com.example.demo.global.MessageKeys.USER_PASSWORD_NOT_BLANK;
import static com.example.demo.global.MessageKeys.USER_PASSWORD_NOT_NULL;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})

@NotNull(message = USER_PASSWORD_NOT_NULL)
@NotBlank(message = USER_PASSWORD_NOT_BLANK)
@Size(min = 5, max = 255, message = USER_PASSWORD_INCORRECT_SIZE)
public @interface PasswordValidation {

    String message() default "Error validation password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
