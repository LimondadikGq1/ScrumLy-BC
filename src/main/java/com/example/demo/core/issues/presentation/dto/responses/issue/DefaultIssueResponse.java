package com.example.demo.core.issues.presentation.dto.responses.issue;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DefaultIssueResponse(

        Long id,

        String name,

        String description,

        Integer story_points,

        LocalDateTime created_at
) {
}
