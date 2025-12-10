package com.example.demo.core.sprints.application.impl;

import com.example.demo.core.issues.exceptions.IssueNotFoundException;
import com.example.demo.core.issues.infrastructure.entity.Issue;
import com.example.demo.core.issues.infrastructure.jpa.IssueRepository;
import com.example.demo.core.projects.exceptions.ProjectNotFoundException;
import com.example.demo.core.projects.infrastructure.jpa.ProjectRepository;
import com.example.demo.core.sprints.Sprint;
import com.example.demo.core.sprints.application.SprintService;
import com.example.demo.core.sprints.exceptions.SprintNotFoundException;
import com.example.demo.core.sprints.infrastructure.jpa.SprintRepository;
import com.example.demo.core.sprints.presentation.dto.requests.AddIssueSprintRequest;
import com.example.demo.core.sprints.presentation.dto.requests.SprintCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

    private final ProjectRepository projectRepository;
    private final SprintRepository sprintRepository;
    private final IssueRepository  issueRepository;
    @Override
    public Sprint createSprint(SprintCreateRequest request) {
        var project = projectRepository.findById(request.projectId())
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        List<AddIssueSprintRequest> issues = request.sprintIssues();
        Sprint sprint =Sprint.builder()
                .name(request.name())
                .project(project)
                .all_points(request.all_points())
                .build();
        Sprint savedSprint = sprintRepository.save(sprint);
        for(AddIssueSprintRequest issue : issues){
            Issue issue1 = issueRepository.findById(issue.sprintId())
                    .orElseThrow(() -> new IssueNotFoundException("Issue not found"));
            issue1.setSprint(savedSprint);
        }
        return savedSprint;

    }

    @Override
    public Sprint closeSprint(Long sprintId) {
        return null;
    }

    @Override
    public Sprint getSprintInfo(Long sprintId) {
        return sprintRepository.findById(sprintId)
                .orElseThrow(() -> new SprintNotFoundException("Sprint not found"));
    }

    @Override
    public List<Issue> getSprintIssues(Long sprintId) {
        return List.of();
    }
}
