package com.example.demo.core.issues.infrastructure.jpa.additionals;

import com.example.demo.core.issues.infrastructure.entity.additionals.Type;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {

    Optional<Type> findById(@NotNull Long typeId);

    Optional<Type> findByName(String name);
}
