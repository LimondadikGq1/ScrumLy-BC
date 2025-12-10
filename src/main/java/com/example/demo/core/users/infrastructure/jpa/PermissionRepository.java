package com.example.demo.core.users.infrastructure.jpa;

import com.example.demo.core.users.infrastructure.entity.Permission;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findById(@NotNull Long id);

    Optional<Permission> findByName(String name);
}
