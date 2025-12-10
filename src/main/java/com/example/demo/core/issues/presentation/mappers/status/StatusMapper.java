package com.example.demo.core.issues.presentation.mappers.status;

import com.example.demo.core.issues.infrastructure.entity.additionals.Status;
import com.example.demo.core.issues.presentation.dto.responses.status.ChangeStatusIssueResponse;
import com.example.demo.core.issues.presentation.dto.responses.status.DefaultStatusResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.SETTER
)
public interface StatusMapper {

    DefaultStatusResponse toDefaultStatusResponse(Status status);

    ChangeStatusIssueResponse toChangeStatusIssueResponse(Status status);

    Status toStatusFromDefaultStatusResponse(DefaultStatusResponse response);

    Status toStatusFromChangeStatusIssueResponse(DefaultStatusResponse response);

}
