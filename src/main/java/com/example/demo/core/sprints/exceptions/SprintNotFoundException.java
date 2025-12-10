package com.example.demo.core.sprints.exceptions;

import com.example.demo.global.exceptions.base.BaseNotFoundException;

public class SprintNotFoundException extends BaseNotFoundException {
    public SprintNotFoundException(String message) {
        super(message);
    }
}
