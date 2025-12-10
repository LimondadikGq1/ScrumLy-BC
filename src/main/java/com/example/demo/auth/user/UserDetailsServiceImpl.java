package com.example.demo.auth.user;

import com.example.demo.core.users.exceptions.UserNotFoundException;
import com.example.demo.core.users.infrastructure.entity.SystemRole;
import com.example.demo.core.users.infrastructure.entity.User;
import com.example.demo.core.users.infrastructure.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.global.exceptions.constants.ExceptionKeys.USER_NOT_FOUND_BY_EMAIL;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findWithRolesAndPermissionsByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_EMAIL));

        SystemRole role = user.getRole();
        log.debug("User role: {}", role);

        return UserDetails
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(role)
                .permissions(role.getPermissions())
                .build();
    }
}
