package com.example.demo.core.issues.application.main;

import com.example.demo.core.issues.application.IssueDto;
import com.example.demo.core.issues.application.additinals.StatusService;
import com.example.demo.core.issues.application.additinals.TypeService;
import com.example.demo.core.issues.application.main.impl.IssueService;
import com.example.demo.core.issues.exceptions.IssueNotFoundException;
import com.example.demo.core.issues.infrastructure.entity.Issue;
import com.example.demo.core.issues.infrastructure.entity.additionals.Status;
import com.example.demo.core.issues.infrastructure.entity.additionals.Type;
import com.example.demo.core.issues.infrastructure.jpa.IssueRepository;
import com.example.demo.core.issues.presentation.dto.requests.ChangeIssueDataRequest;
import com.example.demo.core.issues.presentation.dto.requests.CreateIssueRequest;
import com.example.demo.core.issues.presentation.dto.requests.IssueInfoRequest;
import com.example.demo.core.issues.presentation.dto.requests.status.ChangeStatusRequest;
import com.example.demo.core.issues.presentation.dto.requests.type.ChangeTypeRequest;
import com.example.demo.core.projects.application.ProjectService;
import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.users.application.UserService;
import com.example.demo.core.users.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final ProjectService projectService;

    private final TypeService typeService;

    private final StatusService  statusService;

    private final UserService userService;

    private final IssueRepository issueRepository;

    @Override
    @Transactional
    public Issue createIssue(Long userId, CreateIssueRequest request) {
        var issueData = buildIssueData(userId, request);
        Issue issue = Issue
                .builder()
                .name(request.name())
                .description(request.description())
                .story_points(request.story_points())
                .priority(request.priority())
                .type(issueData.type())
                .author(issueData.user())
                .status(issueData.status())
                .project(issueData.project())
                .build();

        return issueRepository.save(issue);
    }

    @Override
    public Issue getIssueInfoById(Long userId, IssueInfoRequest request) {
        return issueRepository.findById(userId,request.projectId(),request.issueId())
                .orElseThrow(() -> new IssueNotFoundException("Issue not found"));
    }

    @Override
    public Status changeStatus(Long userId, ChangeStatusRequest request) {
         return null;
    }

    @Override
    public Type changeType(Long userId, ChangeTypeRequest request) {
        return null;
    }

    @Override
    public Issue changeData(Long userId, ChangeIssueDataRequest request) {
        return null;
    }

    private IssueDto buildIssueData(Long userId, CreateIssueRequest request){
        Project project = projectService.findProjectById(userId,request.projectId());
        Type type =  typeService.findById(request.typeId());
        Status status = statusService.findById(request.typeId());
        User author = userService.findById(userId);

        return IssueDto.builder()
                .user(author)
                .status(status)
                .type(type)
                .project(project)
                .build();
    }

}
