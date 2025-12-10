package com.example.demo.core.users.presentation.mappers;

import com.example.demo.auth.user.UserDetails;
import com.example.demo.core.users.presentation.dto.responses.DefaultUserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public static DefaultUserResponse toDefaultUserResponse(
            UserDetails userDetails
    ) {
        return DefaultUserResponse
                .builder()
                .id(userDetails.getId())
                .email(userDetails.getEmail())
                .username(userDetails.getOriginalUserName())
                .build();
    }
}
