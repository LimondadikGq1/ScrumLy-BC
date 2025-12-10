package com.example.demo.global.exceptions.base;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class BaseSaveEntityException extends RuntimeException {

    private String param;

    private String[] params;

    public BaseSaveEntityException(String message) {
        super(message);
    }

    public BaseSaveEntityException(String message, String param) {
        super(message);
        this.param = param;
    }

    public BaseSaveEntityException(String message, String... params) {
        super(message);
        this.params = params;
    }

    public BaseSaveEntityException(String message, Number... params) {
        super(message);
        this.params = new String[]{Arrays.toString(params)};
    }
}
