package com.example.demo.core.projects.presentation.dto.requests;

import lombok.Builder;

@Builder
public record ChangeDescriptionRequest(
        String description
) {
}
