package com.example.demo.core.projects.application;

import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.projects.presentation.dto.requests.CreateProjectRequest;
import com.example.demo.core.users.infrastructure.entity.User;
import com.example.demo.global.pagination.impl.SortBy;
import com.example.demo.global.pagination.impl.SortOrder;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {

     Project createProject(CreateProjectRequest projectResponse,
                           Long userId);

     List<Project> findAllUserProjectsСursor(Long userId, Object cursor,
                                             Integer limit,
                                             SortBy sortBy,
                                             SortOrder sortOrder);

     Page<Project> findAllUserProjectsLimitOffset(Long userId, Integer offset,
                                                  Integer limit,
                                                  SortBy sortBy,
                                                  SortOrder sortOrder
     );

     List<User> findAllUsersByProject(Long userId, Long projectId);

     Project findProjectByName(Long userId, String name);

     Project findProjectByKey(Long userId, String key);

     Project findProjectById(Long userId, Long id);
}
