package com.example.demo.core.projects.exceptions;

import com.example.demo.global.exceptions.base.BaseNotFoundException;
import lombok.Getter;

@Getter
public class ProjectRoleNotFoundException extends BaseNotFoundException {

    public ProjectRoleNotFoundException(String message) {
        super(message);
    }

    public ProjectRoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectRoleNotFoundException(String message, String param) {
        super(message, param);
    }

    public ProjectRoleNotFoundException(String message, Number... params) {
        super(message, params);
    }
}
