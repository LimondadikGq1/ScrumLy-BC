package com.example.demo.auth.http.exceptions;

import lombok.Getter;



@Getter
public class AuthentificationException extends RuntimeException {
    private String param;
    private String[] params;

    public AuthentificationException(String message) {
        super(message);
    }

    public AuthentificationException(String message, String... params) {
        super(message);
        this.params = params;
    }

}
