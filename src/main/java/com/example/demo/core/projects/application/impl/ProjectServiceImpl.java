package com.example.demo.core.projects.application.impl;

import com.example.demo.core.colaboration.application.impl.UserProjectRoleService;
import com.example.demo.core.projects.application.ProjectService;
import com.example.demo.core.projects.exceptions.ProjectNotFoundException;
import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.projects.infrastructure.jpa.ProjectRepository;
import com.example.demo.core.projects.presentation.dto.requests.CreateProjectRequest;
import com.example.demo.core.users.infrastructure.entity.User;
import com.example.demo.core.users.infrastructure.jpa.UserRepository;
import com.example.demo.global.pagination.Paginator;
import com.example.demo.global.pagination.impl.SortBy;
import com.example.demo.global.pagination.impl.SortOrder;
import com.example.demo.global.utils.KeyGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.global.exceptions.constants.ExceptionKeys.PROJECT_NOT_FOUND_BY_ID;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.PROJECT_NOT_FOUND_BY_KEY;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.PROJECT_NOT_FOUND_BY_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private static final String CREATOR_PROJECT_ROLE = "OWNER";

    private final ProjectRepository projectRepository;

    private final KeyGenerator projectKeyGenerator;

    private final UserProjectRoleService userProjectRoleService;

    private final Paginator<Project> paginator;

    @Override
    @Transactional
    public Project createProject(CreateProjectRequest projectResponse,
                                 Long userId )
    {
        String key = projectKeyGenerator.generateKey();
        log.info("Generated key:{}",key);

        var project = Project.builder()
                .name(projectResponse.name())
                .description(projectResponse.description())
                .key(key)
                .build();

        Project savedProject =  projectRepository.save(project);
        log.info("Project name:{}",project.getName());

        userProjectRoleService.buildAndSaveUserProjectRole(
                savedProject, userId,
                CREATOR_PROJECT_ROLE);

        return project;
    }

    @Override
    public List<Project> findAllUserProjectsСursor(Long userId,
                                                   Object cursor,
                                                   Integer limit,
                                                   SortBy sortBy,
                                                   SortOrder sortOrder) {
        if(cursor != null){
            log.info("cursor != null");
            return paginator.getNextPage(userId, limit,cursor, sortBy,sortOrder);
        }
        return paginator.getFirstPage(userId,limit,sortBy,sortOrder);
    }

    @Override
    public Page<Project> findAllUserProjectsLimitOffset(
            Long userId,
            Integer offset,
            Integer limit,
            SortBy sortBy,
            SortOrder sortOrder) {

       Page<Project> projects = projectRepository.findAllByUsersAndRoles_UserId(
                       userId,
                       PageRequest.of(offset,limit, Sort.by(
                               Sort.Direction.valueOf(sortOrder.getOrder()), sortBy.getSort())
               ));
       return projects;
    }

    @Override
    public List<User> findAllUsersByProject(Long userId,Long projectId) {
        return projectRepository.findAllUsersByProject(userId, projectId);
    }

    @Override
    public Project findProjectByName(Long userId, String name) {
        return projectRepository.findProjectByName(userId,name)
                .orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_BY_NAME,name));
    }

    @Override
    public Project findProjectByKey(Long userId,String key) {
        return projectRepository.findProjectByKey(userId,key)
                .orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_BY_KEY,key));
    }

    @Override
    public Project findProjectById(Long userId,Long id) {
        return projectRepository.findProjectById(userId,id)
                .orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_BY_ID,id));
    }

    @Override
    public void deleteProject(Long userId, Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    @Transactional
    public void changeName(Long userId, Integer projectId, String name) {
        Project project = projectRepository
                .findById(Long.valueOf(projectId)).orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_BY_ID));
        project.setName(name);
    }

    @Override
    @Transactional
    public void changeDescription(Long userId, Integer projectId, String description) {
        Project project = projectRepository
                .findById(Long.valueOf(projectId)).orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_BY_ID));
        project.setDescription(description);
    }


}
