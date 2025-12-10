package com.example.demo.core.projects.infrastructure.jpa;

import com.example.demo.core.projects.infrastructure.entity.ProjectRole;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRoleRepository extends JpaRepository<ProjectRole,Long> {

     Optional<ProjectRole> findByName(String name);

     Optional<ProjectRole> findById(@NotNull Long id);
}
