package com.example.demo.global.exceptions.responses;

import lombok.Builder;

@Builder
public record SubError(

        String object,

        String field,

        Object rejectedValue,

        String message
) {
}
