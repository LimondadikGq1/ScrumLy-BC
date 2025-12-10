package com.example.demo.core.projects.exceptions;

import com.example.demo.global.exceptions.base.BaseNotFoundException;
import lombok.Getter;

@Getter
public class ProjectNotFoundException extends BaseNotFoundException {

    public ProjectNotFoundException(String message) {
        super(message);
    }

    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectNotFoundException(String message, String param) {
        super(message, param);
    }

    public ProjectNotFoundException(String message, Number... params) {
        super(message, params);
    }
}
