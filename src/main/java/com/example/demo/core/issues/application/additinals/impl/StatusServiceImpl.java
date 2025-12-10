package com.example.demo.core.issues.application.additinals.impl;

import com.example.demo.core.issues.application.additinals.StatusService;
import com.example.demo.core.issues.exceptions.StatusNotFoundException;
import com.example.demo.core.issues.infrastructure.entity.additionals.Status;
import com.example.demo.core.issues.infrastructure.jpa.additionals.StatusRepository;
import com.example.demo.core.issues.presentation.dto.requests.status.CreateStatusRequest;
import com.example.demo.core.projects.exceptions.ProjectNotFoundException;
import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.projects.infrastructure.jpa.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.global.exceptions.constants.ExceptionKeys.PROJECT_NOT_FOUND_BY_ID;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.STATUS_NOT_FOUND_BY_ID;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.STATUS_NOT_FOUND_BY_NAME;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final ProjectRepository projectRepository;

    private final StatusRepository statusRepository;

    @Override
    public Status create(CreateStatusRequest request) {
        Long projectId = request.projectId();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_BY_ID, String.valueOf(projectId)));
        Status type = Status.builder()
                .name(request.name())
                .project(project)
                .is_default(false)
                .build();
        return statusRepository.save(type);
    }

    @Override
    public Status findById(Long statusId) {
        return statusRepository.findById(statusId)
                .orElseThrow(() -> new StatusNotFoundException(STATUS_NOT_FOUND_BY_ID, String.valueOf(statusId)));
    }

    @Override
    public Status findByName(String name) {
        return statusRepository.findByName(name)
                .orElseThrow(() -> new StatusNotFoundException(STATUS_NOT_FOUND_BY_NAME,name));
    }
}
