package com.example.demo.core.projects.presentation.dto.responses;

import lombok.Builder;

@Builder
public record CreateProjectResponse(

        Long id,

        String name,

        String key,

        String description
) {
}
