package com.example.demo.core.users.application;

import com.example.demo.core.users.infrastructure.entity.User;
import com.example.demo.core.users.presentation.dto.requests.ChangePasswordRequest;
import com.example.demo.core.users.presentation.dto.requests.ChangeProfileRequest;
import com.example.demo.core.users.presentation.dto.requests.ChangeUsernameRequest;

public interface UserService {

    User editPassword(Long userId, ChangePasswordRequest request);

    User editUsername(Long userId, ChangeUsernameRequest request);

    User editProfile(Long userId, ChangeProfileRequest request);

    User findById(Long userId);
}
