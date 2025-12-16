package com.example.demo.core.issues.presentation.dto.requests.status;

import lombok.Builder;

@Builder
public record ChangeStatusRequest(
        Long projectId,
        Long issueId,
        Long name
) {
}
