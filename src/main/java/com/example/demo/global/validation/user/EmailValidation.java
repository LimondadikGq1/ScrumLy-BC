package com.example.demo.global.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.demo.global.MessageKeys.USER_EMAIL_INCORRECT;
import static com.example.demo.global.MessageKeys.USER_EMAIL_NOT_BLANK;
import static com.example.demo.global.MessageKeys.USER_EMAIL_NOT_NULL;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})

@Email(message = USER_EMAIL_INCORRECT)
@NotNull(message = USER_EMAIL_NOT_NULL)
@NotBlank(message = USER_EMAIL_NOT_BLANK)
public @interface EmailValidation {

    String message() default "Error validation email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
