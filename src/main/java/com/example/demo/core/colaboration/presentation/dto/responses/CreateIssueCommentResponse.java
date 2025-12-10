package com.example.demo.core.colaboration.presentation.dto.responses;

import lombok.Builder;

@Builder
public record CreateIssueCommentResponse(
        String content,
        Long userId
) {
}
