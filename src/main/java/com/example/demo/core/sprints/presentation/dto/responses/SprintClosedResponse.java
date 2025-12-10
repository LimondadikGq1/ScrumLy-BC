package com.example.demo.core.sprints.presentation.dto.responses;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SprintClosedResponse(
        String name,
        Integer all_points,
        Integer closed_points,
        LocalDateTime start_date,
        LocalDateTime end_date
) {
}
