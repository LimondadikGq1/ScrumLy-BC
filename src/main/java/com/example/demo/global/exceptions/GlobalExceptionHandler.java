package com.example.demo.global.exceptions;


import com.example.demo.global.exceptions.base.BaseNotFoundException;
import com.example.demo.global.exceptions.base.BaseSaveEntityException;
import com.example.demo.global.exceptions.responses.ErrorResponse;
import com.example.demo.global.exceptions.responses.SubError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static com.example.demo.global.exceptions.constants.ExceptionKeys.ERROR_PARSE_JSON;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.INCOMPLETE_INSERTIONS_ERROR;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.METHOD_INVALID_ARGS;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.RESOURSE_NOT_FOUND;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.VALIDATION_FAILED;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    private final Locale locale = LocaleContextHolder.getLocale();

    @ExceptionHandler(BaseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUnhandledException(
            BaseNotFoundException ex,
            HttpServletRequest servletRequest
    ) {
        String messsage = getLocalMessage(ex.getMessage(), ex.getParams());
        log.info("Catch exception on not found entity: {}", messsage);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(messsage)
                        .timestamp(LocalDateTime.now())
                        .path(servletRequest.getRequestURI())
                        .build()
                );
    }

    @ExceptionHandler(BaseSaveEntityException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            BaseSaveEntityException ex,
            HttpServletRequest servletRequest
    ) {
        String message = getLocalMessage(ex.getMessage(), ex.getParams());
        log.info("Catch exception on save entity: {}", message);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .path(servletRequest.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(HttpStatus.NOT_FOUND.value())
                        .build()
                );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleUnhandledException(
            ConstraintViolationException ex, HttpServletRequest servletRequest
    ) {
        String message = getLocalMessage(VALIDATION_FAILED);
        log.info("Catch exception on invalid request: {}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
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
                        .message(getLocalMessage(error.getDefaultMessage()))
                        .build()
                ).toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(getLocalMessage(VALIDATION_FAILED))
                        .timestamp(LocalDateTime.now())
                        .path(servletRequest.getRequestURI())
                        .subErrors(subErrors)
                        .build()
                );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResourceFoundException(
            NoResourceFoundException ex
    ) {
        String message = getLocalMessage(RESOURSE_NOT_FOUND);
        log.info("Catch exception on resourse found: {}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleNoResourceFoundException(
            MethodArgumentTypeMismatchException ex
    ) {
        String message = getLocalMessage(METHOD_INVALID_ARGS);
        log.info("Catch exception on method mismatch request: {}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleEnumNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest servletRequest
    ) {
        String message = getLocalMessage(ERROR_PARSE_JSON);
        log.info("Catch exception on parse json request: {}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .path(servletRequest.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build()
                );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleIncompleteInsertions(
            DataIntegrityViolationException ex,
            HttpServletRequest servletRequest
    ) {
        String message = getLocalMessage(INCOMPLETE_INSERTIONS_ERROR);
        log.info("Catch exception on parse json request: {}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .path(servletRequest.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build()
                );
    }

    public String getLocalMessage(String messsage, Object[] args) {
        return messageSource.getMessage(messsage, args, locale);
    }

    public String getLocalMessage(String messsage) {
        return messageSource.getMessage(messsage, null, locale);
    }
}
