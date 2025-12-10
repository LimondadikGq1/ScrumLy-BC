package com.example.demo.auth.http.exceptions;

import lombok.Getter;

@Getter
public class AuthentificationException extends RuntimeException {

    public AuthentificationException(String message) {
        super(message);
    }
}
