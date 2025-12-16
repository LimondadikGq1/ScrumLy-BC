package com.example.demo.global.exceptions.base;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class BaseNotFoundException extends RuntimeException {

    private String param;

    private String[] params;

    public BaseNotFoundException(String message) {
        super(message);
    }

    public BaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseNotFoundException(String message, String ...params) {
        super(message);
        this.params = params;
    }

    public BaseNotFoundException(String message, Number... params) {
        super(message);
        this.params = new String[]{Arrays.toString(params)};
    }
}
