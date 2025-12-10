package com.example.demo.core.users.exceptions;

import com.example.demo.global.exceptions.base.BaseSaveEntityException;
import lombok.Getter;

@Getter
public class UserSaveEntityException extends BaseSaveEntityException {

    public UserSaveEntityException(String message) {
        super(message);
    }

    public UserSaveEntityException(String message, String param) {
        super(message, param);
    }

    public UserSaveEntityException(String message, String... params) {
        super(message, params);
    }

    public UserSaveEntityException(String message, Number... params) {
        super(message, params);
    }
}
