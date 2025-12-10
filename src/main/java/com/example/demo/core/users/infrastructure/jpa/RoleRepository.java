package com.example.demo.core.users.infrastructure.jpa;

import com.example.demo.core.users.infrastructure.entity.SystemRole;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<SystemRole, Long> {

    Optional<SystemRole> findById(@NotNull Long id);

    Optional<SystemRole> findByName(@NotNull String name);
}
