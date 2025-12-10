package com.example.demo.core.sprints.presentation.dto.requests;

import lombok.Builder;

@Builder
public record AddIssueSprintRequest(
        Long sprintId
) {
}
