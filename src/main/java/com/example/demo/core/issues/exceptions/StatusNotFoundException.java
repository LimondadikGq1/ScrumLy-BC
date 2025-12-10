package com.example.demo.core.issues.exceptions;

import com.example.demo.global.exceptions.base.BaseNotFoundException;
import lombok.Getter;

@Getter
public class StatusNotFoundException extends BaseNotFoundException {

    public StatusNotFoundException(String message) {
        super(message);
    }

    public StatusNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusNotFoundException(String message, String param) {
        super(message, param);
    }

    public StatusNotFoundException(String message, Number... params) {
        super(message, params);
    }
}
