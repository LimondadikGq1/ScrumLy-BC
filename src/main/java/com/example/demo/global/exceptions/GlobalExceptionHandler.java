package com.example.demo.global.exceptions;

import com.example.demo.global.exceptions.responses.ErrorResponse;
import com.example.demo.global.exceptions.responses.SubError;
import com.example.demo.global.exceptions.services.MessageSourceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.global.exceptions.constants.ExceptionKeys.VALIDATION_FAILED;


@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSourceService messageSource;



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleUnhandledException(
            ConstraintViolationException ex, HttpServletRequest servletRequest
    ) {
        String message = messageSource.getLocalMessage(VALIDATION_FAILED);
        log.info("Catch exception on invalid request: {}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builderDefault()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(message)
                        .timestamp(LocalDateTime.now())
                        .path(servletRequest.getRequestURI())
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, HttpServletRequest servletRequest
    ) {
        log.info("Catch exception on invalid request: {}", ex.getMessage());

        List<SubError> subErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> SubError.builder()
                        .object(error.getObjectName())
                        .field(error.getField())
                        .rejectedValue(error.getRejectedValue())
                        .message(messageSource.getLocalMessage(error.getDefaultMessage()))
                        .build()
                ).toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builderFields()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(messageSource.getLocalMessage(VALIDATION_FAILED))
                        .timestamp(LocalDateTime.now())
                        .path(servletRequest.getRequestURI())
                        .subErrors(subErrors)
                        .build()
                );
    }
}
