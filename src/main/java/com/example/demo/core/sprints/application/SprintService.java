package com.example.demo.core.sprints.application;

import com.example.demo.core.issues.infrastructure.entity.Issue;
import com.example.demo.core.sprints.Sprint;
import com.example.demo.core.sprints.presentation.dto.requests.SprintCreateRequest;

import java.util.List;

public interface SprintService {
    Sprint createSprint(SprintCreateRequest request);
    Sprint closeSprint(Long sprintId);
    Sprint getSprintInfo(Long sprintId);
    List<Issue> getSprintIssues(Long sprintId);
}
