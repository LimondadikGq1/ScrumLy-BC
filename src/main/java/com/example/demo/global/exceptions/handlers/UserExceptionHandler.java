package com.example.demo.global.exceptions.handlers;

import com.example.demo.auth.http.exceptions.AuthentificationException;
import com.example.demo.global.exceptions.base.BaseNotFoundException;
import com.example.demo.global.exceptions.base.BaseSaveEntityException;
import com.example.demo.global.exceptions.responses.ErrorResponse;
import com.example.demo.global.exceptions.services.MessageSourceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static com.example.demo.global.exceptions.constants.ExceptionKeys.INCOMPLETE_INSERTIONS_ERROR;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class UserExceptionHandler {

    private final MessageSourceService messageSource;

    @ExceptionHandler(BaseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUnhandledException(
            BaseNotFoundException ex,
            HttpServletRequest servletRequest
    ) {
        String messsage = messageSource.getLocalMessage(ex.getMessage(), ex.getParams());
        log.info(ex.getMessage());
        log.info("Catch exception on not found entity: {}", messsage);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builderDefault()
                        .status(HttpStatus.NOT_FOUND.value())
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
        String message = messageSource.getLocalMessage(ex.getMessage(), ex.getParams());
        log.info("Catch exception on save entity: {}", message);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builderDefault()
                        .path(servletRequest.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(HttpStatus.NOT_FOUND.value())
                        .build()
                );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleIncompleteInsertions(
            DataIntegrityViolationException ex,
            HttpServletRequest servletRequest
    ) {
        String message = messageSource.getLocalMessage(INCOMPLETE_INSERTIONS_ERROR);
        log.info("Catch exception on parse json request: {}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builderDefault()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(message)
                        .path(servletRequest.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(AuthentificationException.class)
    public ResponseEntity<ErrorResponse> handleAuthentificationException(
            AuthentificationException ex,
            HttpServletRequest servletRequest
    ) {
        String messsage = messageSource.getLocalMessage(ex.getMessage(), ex.getParams());
        log.info(ex.getMessage());
        log.info("Catch exception on not found entity: {}", messsage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builderDefault()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(messsage)
                        .timestamp(LocalDateTime.now())
                        .path(servletRequest.getRequestURI())
                        .build()
                );
    }

}
