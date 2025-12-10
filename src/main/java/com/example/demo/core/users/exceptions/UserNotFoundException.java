package com.example.demo.core.users.exceptions;

import com.example.demo.global.exceptions.base.BaseNotFoundException;
import lombok.Getter;

@Getter
public class UserNotFoundException extends BaseNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(String message, String param) {
        super(message, param);
    }

    public UserNotFoundException(String message, Number... params) {
        super(message, params);
    }
}
