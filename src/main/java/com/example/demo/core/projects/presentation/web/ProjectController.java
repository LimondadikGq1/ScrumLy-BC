package com.example.demo.core.projects.presentation.web;

import com.example.demo.auth.user.UserDetails;
import com.example.demo.core.projects.application.ProjectService;
import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.projects.presentation.dto.requests.CreateProjectRequest;
import com.example.demo.core.projects.presentation.dto.responses.CreateProjectResponse;
import com.example.demo.core.projects.presentation.mappers.ProjectMapper;
import com.example.demo.core.users.infrastructure.entity.User;
import com.example.demo.core.users.presentation.dto.responses.DefaultUserResponse;
import com.example.demo.core.users.presentation.mappers.UserMapper;
import com.example.demo.global.pagination.impl.SortBy;
import com.example.demo.global.pagination.impl.SortOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.core.projects.presentation.web.ProjectPaths.BY_ID;
import static com.example.demo.core.projects.presentation.web.ProjectPaths.BY_KEY;
import static com.example.demo.core.projects.presentation.web.ProjectPaths.BY_NAME;
import static com.example.demo.core.projects.presentation.web.ProjectPaths.BY_PROJECT;
import static com.example.demo.core.projects.presentation.web.ProjectPaths.BY_USER;
import static com.example.demo.core.projects.presentation.web.ProjectPaths.ID;
import static com.example.demo.core.projects.presentation.web.ProjectPaths.KEY;
import static com.example.demo.core.projects.presentation.web.ProjectPaths.NAME;
import static com.example.demo.core.projects.presentation.web.ProjectPaths.PROJECT_BASE_URL;
import static com.example.demo.core.projects.presentation.web.ProjectPaths.SEARCH;
import static com.example.demo.core.projects.presentation.web.ProjectPaths.V1;
import static com.example.demo.core.projects.presentation.web.ProjectPaths.V2;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = PROJECT_BASE_URL)
public class ProjectController {

    private final ProjectService projectService;

    private final ProjectMapper projectMapper;

    private final UserMapper userMapper;

    @PostMapping(path = V1 + "/")
    public ResponseEntity<CreateProjectResponse> createProject(
            @RequestBody @Valid CreateProjectRequest projectRequest,
            @AuthenticationPrincipal UserDetails user
    ){
        log.info("Get auth user id: {}",user.getId());
        Project project = projectService.createProject(projectRequest,user.getId());
        CreateProjectResponse response = projectMapper.toCreateProjectResponse(project);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = V1 + SEARCH + BY_USER)
    public ResponseEntity<List<CreateProjectResponse>> getAllUserProjectsCursor(
            @RequestParam(value = "cursor",required = false) Object cursor,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "sort",defaultValue = "ID") SortBy sortBy,
            @RequestParam(value = "sortBy", defaultValue = "ASC") SortOrder sortOrder,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        log.info("cursor:{}, limit:{}, sort:{}, sortBy:{}",cursor,limit,sortBy.getSort(),sortOrder.getOrder());
        Long userId = userDetails.getId();
        List<Project> projects = projectService.findAllUserProjectsСursor(userId,cursor,limit,sortBy,sortOrder);
        List<CreateProjectResponse> createProjectRespons = projectMapper.toListCreateProjectResponse(projects);
        return ResponseEntity.ok(createProjectRespons);
    }
    @GetMapping(path = V2 + SEARCH + BY_USER)
    public ResponseEntity<List<CreateProjectResponse>> getAllUserProjectsLimitOffset(
            @RequestParam(value = "offset",required = false, defaultValue = "5") Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "sort",defaultValue = "ID") SortBy sortBy,
            @RequestParam(value = "sortBy", defaultValue = "ASC") SortOrder sortOrder,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        log.info("cursor:{}, limit:{}, sort:{}, sortBy:{}",offset,limit,sortBy.getSort(),sortOrder.getOrder());
        Long userId = userDetails.getId();
        Page<Project> projects = projectService.findAllUserProjectsLimitOffset(userId,offset,limit,sortBy,sortOrder);
        List<CreateProjectResponse> createProjectRespons = projectMapper.toListCreateProjectResponse(projects.getContent());
        return ResponseEntity.ok(createProjectRespons);
    }

    @GetMapping(path = V1 + SEARCH + BY_PROJECT + ID)
    public ResponseEntity<List<DefaultUserResponse>> getAllUsersByProject(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long projectId
    ){
        Long userId = userDetails.getId();
        log.info("Get auth user id: {}",userId);

        List<User> users  = projectService.findAllUsersByProject(userId,projectId);
        List<DefaultUserResponse> response = userMapper.toListDefaultUserResponse(users);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = V1 + SEARCH + BY_ID + ID)
    public ResponseEntity<CreateProjectResponse> getProjectById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long id)
    {
        Project project = projectService.findProjectById(userDetails.getId(), id);
        log.info("Get project id: {} and name: {}", project.getId(),project.getName());

        CreateProjectResponse response = projectMapper.toCreateProjectResponse(project);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = V1 + SEARCH + BY_NAME + NAME)
    public ResponseEntity<CreateProjectResponse> getProjectByName(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("name") String name)
    {
        Project project = projectService.findProjectByName(userDetails.getId(), name);
        log.info("Get project id: {} and name: {}", project.getId(),project.getName());

        CreateProjectResponse response = projectMapper.toCreateProjectResponse(project);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = V1 + SEARCH + BY_KEY + KEY)
    public ResponseEntity<CreateProjectResponse> getProjectByKey(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("key") String key)
    {
        Project project = projectService.findProjectByKey(userDetails.getId(), key);
        log.info("Get project id: {} and name: {}", project.getId(),project.getName());

        CreateProjectResponse response = projectMapper.toCreateProjectResponse(project);
        return ResponseEntity.ok(response);
    }
}
