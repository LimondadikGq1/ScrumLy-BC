package com.example.demo.core.issues.exceptions;

import com.example.demo.global.exceptions.base.BaseNotFoundException;
import lombok.Getter;

@Getter
public class IssueNotFoundException extends BaseNotFoundException {

    public IssueNotFoundException(String message) {
        super(message);
    }

    public IssueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IssueNotFoundException(String message, String param) {
        super(message, param);
    }

    public IssueNotFoundException(String message, Number... params) {
        super(message, params);
    }
}
