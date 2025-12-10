package com.example.demo.core.issues.presentation.dto.responses.issue;

import com.example.demo.core.issues.infrastructure.entity.enums.Priority;
import com.example.demo.core.issues.presentation.dto.responses.status.DefaultStatusResponse;
import com.example.demo.core.issues.presentation.dto.responses.type.DefaultTypeResponse;
import com.example.demo.core.users.presentation.dto.responses.DefaultUserResponse;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FullDataIssueResponse(

        Long id,

        String name,

        String description,

        Priority priority,

        DefaultTypeResponse type,

        DefaultStatusResponse status,

        Integer story_points,

        DefaultUserResponse author,

        LocalDateTime created_at
) {
}
