package com.example.demo.global.exceptions.handlers;

import com.example.demo.global.exceptions.responses.ErrorResponse;
import com.example.demo.global.exceptions.services.MessageSourceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

import static com.example.demo.global.exceptions.constants.ExceptionKeys.ERROR_PARSE_JSON;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.METHOD_INVALID_ARGS;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.RESOURSE_NOT_FOUND;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class AdditionalRequestExceptionHandler {

    private final MessageSourceService messageSource;

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(
            HttpServletRequest servletRequest,
            NoResourceFoundException ex
    ) {
        String message = messageSource.getLocalMessage(RESOURSE_NOT_FOUND);
        log.info("Catch exception on resourse found: {}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builderDefault()
                        .path(servletRequest.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(
            HttpServletRequest servletRequest,
            MethodArgumentTypeMismatchException ex
    ) {
        String message = messageSource.getLocalMessage(METHOD_INVALID_ARGS);
        log.info("Catch exception on method mismatch request: {}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builderDefault()
                        .path(servletRequest.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build()
                );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleEnumNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest servletRequest
    ) {
        String message = messageSource.getLocalMessage(ERROR_PARSE_JSON);
        log.info("Catch exception on parse json request: {}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builderDefault()
                        .path(servletRequest.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build()
                );
    }
}
