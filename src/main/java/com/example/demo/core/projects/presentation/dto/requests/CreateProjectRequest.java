package com.example.demo.core.projects.presentation.dto.requests;

import com.example.demo.global.validation.project.DescriptionValidation;
import com.example.demo.global.validation.project.NameValidation;
import lombok.Builder;

@Builder
public record CreateProjectRequest(

        @NameValidation
        String name,

        @DescriptionValidation
        String description
) {
}
