package com.example.demo.core.colaboration.presentation.dto.requests;

import lombok.Builder;

@Builder
public record CreateIssueCommentRequest(
        String content,
        Long issueId,
        Long projectId,
        Long userId
) {
}
