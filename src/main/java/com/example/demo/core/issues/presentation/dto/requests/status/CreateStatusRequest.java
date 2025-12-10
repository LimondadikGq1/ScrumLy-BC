package com.example.demo.core.issues.presentation.dto.requests.status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static com.example.demo.global.MessageKeys.ISSUE_PROJECT_ID_NOT_NULL;
import static com.example.demo.global.MessageKeys.ISSUE_STATUS_NAME_NOT_BLANK;
import static com.example.demo.global.MessageKeys.ISSUE_STATUS_NAME_NOT_NULL;

@Builder
public record CreateStatusRequest(

        @NotNull(message = ISSUE_STATUS_NAME_NOT_NULL)
        @NotBlank(message = ISSUE_STATUS_NAME_NOT_BLANK)
        String name,

        @NotNull(message = ISSUE_PROJECT_ID_NOT_NULL)
        Long projectId
) {
}
