package com.example.demo.core.projects.presentation.mappers;

import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.projects.presentation.dto.responses.CreateProjectResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.SETTER
)
public interface ProjectMapper {

    CreateProjectResponse toCreateProjectResponse(Project project);

    List<CreateProjectResponse> toListCreateProjectResponse(List<Project> projects);

    Project toProjectFromCreateProjectResponse(CreateProjectResponse response);

}
