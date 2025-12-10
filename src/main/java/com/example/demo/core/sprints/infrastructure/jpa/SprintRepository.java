package com.example.demo.core.sprints.infrastructure.jpa;

import com.example.demo.core.sprints.Sprint;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SprintRepository  extends JpaRepository<Sprint,Long> {
    Optional<Sprint> findById(@NotNull Long sprintId);
}
