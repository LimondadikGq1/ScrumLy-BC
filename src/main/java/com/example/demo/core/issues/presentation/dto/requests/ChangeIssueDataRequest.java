package com.example.demo.core.issues.presentation.dto.requests;

import com.example.demo.core.issues.infrastructure.entity.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static com.example.demo.global.MessageKeys.ISSUE_DESCRIPTION_NOT_BLANK;
import static com.example.demo.global.MessageKeys.ISSUE_NAME_NOT_BLANK;
import static com.example.demo.global.MessageKeys.ISSUE_NAME_NOT_NULL;
import static com.example.demo.global.MessageKeys.ISSUE_PRIORITY_NOT_NULL;
import static com.example.demo.global.MessageKeys.ISSUE_PROJECT_ID_NOT_NULL;
import static com.example.demo.global.MessageKeys.ISSUE_STATUS_ID_NOT_NULL;
import static com.example.demo.global.MessageKeys.ISSUE_TYPE_ID_NOT_NULL;

@Builder
public record ChangeIssueDataRequest(

        @NotNull(message = ISSUE_NAME_NOT_NULL)
        @NotBlank(message = ISSUE_NAME_NOT_BLANK)
        String name,

        @NotNull(message = ISSUE_DESCRIPTION_NOT_BLANK)
        @NotBlank(message = ISSUE_DESCRIPTION_NOT_BLANK)
        String description,

        @NotNull(message = ISSUE_NAME_NOT_NULL)
        @NotBlank(message = ISSUE_PRIORITY_NOT_NULL)
        Priority priority,

        @NotNull(message = ISSUE_TYPE_ID_NOT_NULL)
        Long typeId,

        Integer story_points,

        @NotNull(message = ISSUE_STATUS_ID_NOT_NULL)
        Long statusId,

        @NotNull(message = ISSUE_PROJECT_ID_NOT_NULL)
        Long projectId
) {
}
