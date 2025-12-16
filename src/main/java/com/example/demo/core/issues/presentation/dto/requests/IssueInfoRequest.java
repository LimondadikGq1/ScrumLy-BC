package com.example.demo.core.issues.presentation.dto.requests;

import lombok.Builder;

@Builder
public record IssueInfoRequest(
        Long projectId,
        Long issueId
) {
}
