package com.example.demo.core.users.presentation.web.profile;

import com.example.demo.auth.user.UserDetails;
import com.example.demo.core.users.application.UserService;
import com.example.demo.core.users.infrastructure.entity.User;
import com.example.demo.core.users.presentation.dto.requests.ChangePasswordRequest;
import com.example.demo.core.users.presentation.dto.requests.ChangeProfileRequest;
import com.example.demo.core.users.presentation.dto.requests.ChangeUsernameRequest;
import com.example.demo.core.users.presentation.dto.responses.DefaultUserResponse;
import com.example.demo.core.users.presentation.mappers.UserDetailsMapper;
import com.example.demo.core.users.presentation.mappers.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.core.users.presentation.web.profile.ProfileUserPaths.CHANGE;
import static com.example.demo.core.users.presentation.web.profile.ProfileUserPaths.PASSWORD;
import static com.example.demo.core.users.presentation.web.profile.ProfileUserPaths.PROFILE;
import static com.example.demo.core.users.presentation.web.profile.ProfileUserPaths.PROFILE_USER_BASE_PATH;
import static com.example.demo.core.users.presentation.web.profile.ProfileUserPaths.PROFILE_USER_INFO;
import static com.example.demo.core.users.presentation.web.profile.ProfileUserPaths.USERNAME;
import static com.example.demo.core.users.presentation.web.profile.ProfileUserPaths.V1;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = PROFILE_USER_BASE_PATH)
public class ProfileUserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping(path = V1 + PROFILE_USER_INFO)
    public ResponseEntity<DefaultUserResponse> getUserInfo(
            @AuthenticationPrincipal UserDetails userDetails)
    {
        log.info("Get auth user id: {}", userDetails.getId());
        DefaultUserResponse user = UserDetailsMapper.toDefaultUserResponse(userDetails);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(path = V1 + CHANGE + PASSWORD)
    public ResponseEntity<DefaultUserResponse> changePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid ChangePasswordRequest request
    ) {
        log.info("req: {}",request);
        Long userId = userDetails.getId();
        log.info("Get auth user id: {}", userDetails.getId());

        User user = userService.editPassword(userId, request);

        DefaultUserResponse response = userMapper.toDefaultUserResponse(user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = V1 + CHANGE + USERNAME)
    public ResponseEntity<DefaultUserResponse> changeUsername(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid ChangeUsernameRequest request
    ) {
        Long userId = userDetails.getId();
        log.info("Get auth user id: {}", userDetails.getId());

        User user = userService.editUsername(userId, request);

        DefaultUserResponse response = userMapper.toDefaultUserResponse(user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = V1 + CHANGE + PROFILE)
    public ResponseEntity<DefaultUserResponse> changeProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid ChangeProfileRequest request
    ) {
        Long userId = userDetails.getId();
        log.info("Get auth user id: {}", userDetails.getId());

        User user = userService.editProfile(userId, request);

        DefaultUserResponse response = userMapper.toDefaultUserResponse(user);
        return ResponseEntity.ok(response);
    }
}
