package com.example.demo.core.issues.presentation.dto.requests.type;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static com.example.demo.global.MessageKeys.ISSUE_TYPE_NAME_NOT_BLANK;
import static com.example.demo.global.MessageKeys.ISSUE_TYPE_NAME_NOT_NULL;

@Builder
public record CreateTypeRequest(

        @NotNull(message = ISSUE_TYPE_NAME_NOT_NULL)
        @NotBlank(message = ISSUE_TYPE_NAME_NOT_BLANK)
        String name,


        Long projectId
){
}
