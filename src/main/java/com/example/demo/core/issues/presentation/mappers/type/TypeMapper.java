package com.example.demo.core.issues.presentation.mappers.type;

import com.example.demo.core.issues.infrastructure.entity.additionals.Type;
import com.example.demo.core.issues.presentation.dto.responses.status.ChangeStatusIssueResponse;
import com.example.demo.core.issues.presentation.dto.responses.type.DefaultTypeResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.SETTER
)
public interface TypeMapper {

    DefaultTypeResponse toDefaultTypeResponse(Type type);

    ChangeStatusIssueResponse toChangeStatusIssueResponse(Type type);

    Type toTypeFromDefaultTypeResponse(DefaultTypeResponse response);

    Type toTypeFromChangeStatusIssueResponse(Type type);
}
