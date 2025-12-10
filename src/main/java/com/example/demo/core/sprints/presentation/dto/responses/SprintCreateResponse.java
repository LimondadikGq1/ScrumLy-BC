package com.example.demo.core.sprints.presentation.dto.responses;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record SprintCreateResponse(
        String name,
        Integer all_points,
        LocalDateTime start_date
) {
}
