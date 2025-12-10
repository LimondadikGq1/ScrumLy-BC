package com.example.demo.core.issues.application;

import com.example.demo.core.issues.infrastructure.entity.additionals.Status;
import com.example.demo.core.issues.infrastructure.entity.additionals.Type;
import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.users.infrastructure.entity.User;
import lombok.Builder;

@Builder
public record IssueDto(

        Project project,

        User user,

        Status status,

        Type type
) {
}
