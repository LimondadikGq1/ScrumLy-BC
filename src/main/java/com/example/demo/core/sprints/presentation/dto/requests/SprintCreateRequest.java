package com.example.demo.core.sprints.presentation.dto.requests;

import lombok.Builder;

import java.util.List;

@Builder
public record SprintCreateRequest(
        String name,
        Integer all_points,
        Long projectId,
        List<AddIssueSprintRequest> sprintIssues
) {
}
