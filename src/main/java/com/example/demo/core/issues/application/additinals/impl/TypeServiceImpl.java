package com.example.demo.core.issues.application.additinals.impl;

import com.example.demo.core.issues.application.additinals.TypeService;
import com.example.demo.core.issues.exceptions.TypeNotFoundException;
import com.example.demo.core.issues.infrastructure.entity.additionals.Type;
import com.example.demo.core.issues.infrastructure.jpa.additionals.TypeRepository;
import com.example.demo.core.issues.presentation.dto.requests.status.CreateStatusRequest;
import com.example.demo.core.projects.exceptions.ProjectNotFoundException;
import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.projects.infrastructure.jpa.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.global.exceptions.constants.ExceptionKeys.PROJECT_NOT_FOUND_BY_ID;
import static com.example.demo.global.exceptions.constants.ExceptionKeys.TYPE_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final ProjectRepository projectRepository;

    private final TypeRepository typeRepository;

    @Override
    public Type create(CreateStatusRequest request) {
        Long projectId = request.projectId();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_BY_ID, String.valueOf(projectId)));
        Type type = Type.builder()
                .name(request.name())
                .project(project)
                .is_default(false)
                .build();
        return typeRepository.save(type);
    }

    @Override
    public Type findById(Long typeId) {
        return typeRepository.findById(typeId)
                .orElseThrow(() -> new TypeNotFoundException(TYPE_NOT_FOUND_BY_ID, String.valueOf(typeId)));
    }

    @Override
    public Type findByName(String name) {
        return typeRepository.findByName(name)
                .orElseThrow(() -> new TypeNotFoundException(TYPE_NOT_FOUND_BY_ID,name));
    }
}
