package com.example.demo.core.issues.exceptions;

import com.example.demo.global.exceptions.base.BaseNotFoundException;
import lombok.Getter;

@Getter
public class TypeNotFoundException extends BaseNotFoundException {

    public TypeNotFoundException(String message) {
        super(message);
    }

    public TypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeNotFoundException(String message, String param) {
        super(message, param);
    }

    public TypeNotFoundException(String message, Number... params) {
        super(message, params);
    }
}
