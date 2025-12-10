package com.example.demo.core.issues.presentation.mappers.issue;

import com.example.demo.core.issues.infrastructure.entity.Issue;
import com.example.demo.core.issues.presentation.dto.responses.issue.FullDataIssueResponse;
import com.example.demo.core.issues.presentation.mappers.status.StatusMapper;
import com.example.demo.core.issues.presentation.mappers.type.TypeMapper;
import com.example.demo.core.projects.presentation.mappers.ProjectMapper;
import com.example.demo.core.users.presentation.mappers.UserMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.SETTER,
        uses = {
                StatusMapper.class, TypeMapper.class,
                ProjectMapper.class, UserMapper.class
        }
)
public interface IssueMapper {

    FullDataIssueResponse toFullDataIssueResponse(Issue issue);

}