package com.example.demo.core.issues.presentation.dto.responses.type;

import lombok.Builder;

@Builder
public record DefaultTypeResponse(

        Long id,

        String name
) {
}
