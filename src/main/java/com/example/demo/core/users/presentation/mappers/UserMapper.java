package com.example.demo.core.users.presentation.mappers;

import com.example.demo.core.users.infrastructure.entity.User;
import com.example.demo.core.users.presentation.dto.responses.DefaultUserResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.SETTER
)
public interface UserMapper {

    DefaultUserResponse toDefaultUserResponse(User user);

    List<DefaultUserResponse> toListDefaultUserResponse(List<User> defaultUserResponses);


    User toUserFromDefaultUserResponse(DefaultUserResponse user);
}
