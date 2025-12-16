package com.example.demo.core.issues.presentation.dto.requests.type;

import lombok.Builder;

@Builder
public record ChangeTypeRequest(
        Long userId,
        Long projectId,
        Long issueId,
        Long typeId,
        Long name
) {
}
