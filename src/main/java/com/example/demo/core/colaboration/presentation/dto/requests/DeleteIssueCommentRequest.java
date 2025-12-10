package com.example.demo.core.colaboration.presentation.dto.requests;

import lombok.Builder;

@Builder
public record DeleteIssueCommentRequest(
        Long issueId
) {
}
