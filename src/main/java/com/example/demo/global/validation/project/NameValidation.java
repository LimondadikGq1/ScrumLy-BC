package com.example.demo.global.validation.project;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.demo.global.MessageKeys.PROJECT_NAME_NOT_BLANK;
import static com.example.demo.global.MessageKeys.PROJECT_NAME_NOT_NULL;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})

@NotNull(message = PROJECT_NAME_NOT_NULL)
@NotBlank(message = PROJECT_NAME_NOT_BLANK)
public @interface NameValidation {

    String message() default "Error validation email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
