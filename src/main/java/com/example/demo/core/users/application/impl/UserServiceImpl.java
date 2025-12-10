package com.example.demo.core.users.application.impl;

import com.example.demo.core.users.application.UserService;
import com.example.demo.core.users.exceptions.UserNotFoundException;
import com.example.demo.core.users.infrastructure.entity.User;
import com.example.demo.core.users.infrastructure.jpa.UserRepository;
import com.example.demo.core.users.presentation.dto.requests.ChangePasswordRequest;
import com.example.demo.core.users.presentation.dto.requests.ChangeProfileRequest;
import com.example.demo.core.users.presentation.dto.requests.ChangeUsernameRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.global.exceptions.constants.ExceptionKeys.USER_NOT_FOUND_BY_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User editPassword(Long userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID, userId));
        log.info("Get user: {}",user.getId());

        String hashedPass = passwordEncoder.encode(request.newPassword());

        user.setPassword(hashedPass);
        return user;
    }

    @Override
    @Transactional
    public User editUsername(Long userId, ChangeUsernameRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID,userId));

        user.setUsername(request.newUsername());

        return user;
    }

    @Override
    @Transactional
    public User editProfile(Long userId, ChangeProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID,userId));

        String hashedPass = passwordEncoder.encode(request.password());

        user.setPassword(hashedPass);
        user.setUsername(request.username());

        return user;
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID,userId));
    }
}
