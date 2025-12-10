package com.example.demo.core.users.exceptions;

import com.example.demo.global.exceptions.base.BaseNotFoundException;
import lombok.Getter;

@Getter
public class SystemRoleNotFoundException extends BaseNotFoundException {

    public SystemRoleNotFoundException(String message) {
        super(message);
    }

    public SystemRoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemRoleNotFoundException(String message, String param) {
        super(message, param);
    }

    public SystemRoleNotFoundException(String message, Number... params) {
        super(message, params);
    }
}
