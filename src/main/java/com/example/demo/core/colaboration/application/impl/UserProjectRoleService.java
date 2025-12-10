package com.example.demo.core.colaboration.application.impl;

import com.example.demo.core.colaboration.infrastructure.entity.UserProjectRole;
import com.example.demo.core.issues.infrastructure.jpa.UserProjectRoleRepository;
import com.example.demo.core.projects.exceptions.ProjectRoleNotFoundException;
import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.projects.infrastructure.entity.ProjectRole;
import com.example.demo.core.projects.infrastructure.jpa.ProjectRoleRepository;
import com.example.demo.core.users.exceptions.UserNotFoundException;
import com.example.demo.core.users.infrastructure.entity.User;
import com.example.demo.core.users.infrastructure.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.global.exceptions.constants.ExceptionKeys.PROJECT_NOT_FOUND_BY_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProjectRoleService {

    private final UserRepository userRepository;

    private final ProjectRoleRepository projectRoleRepository;

    private final UserProjectRoleRepository userProjectRoleRepository;

    @Transactional
    public void buildAndSaveUserProjectRole(Project project, Long userId,String role){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id: %d - not found".formatted(userId)));
        log.info("Get user id: {}", user.getId());

        ProjectRole defaultProjectRole = projectRoleRepository.findByName(role)
                .orElseThrow(() -> new ProjectRoleNotFoundException(PROJECT_NOT_FOUND_BY_NAME));
        log.info("Get project role:{}",defaultProjectRole);

        var userProjectRole = UserProjectRole
                .builder()
                .user(user)
                .project(project)
                .projectRole(defaultProjectRole)
                .build();

        userProjectRoleRepository.save(userProjectRole);
    }

    public List<UserProjectRole> findAllUserProjectsById(Long userId){
        return userProjectRoleRepository.findUserProjectsById(userId);
    }
}
