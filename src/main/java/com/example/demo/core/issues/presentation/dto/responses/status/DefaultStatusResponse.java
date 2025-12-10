package com.example.demo.core.issues.presentation.dto.responses.status;

import lombok.Builder;

@Builder
public record DefaultStatusResponse(

        Long id,

        String name
) {
}
