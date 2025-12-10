package com.example.demo.core.issues.infrastructure.jpa.additionals;

import com.example.demo.core.issues.infrastructure.entity.additionals.Status;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository  extends JpaRepository<Status, Long> {

    Optional<Status> findById(@NotNull Long statusId);

    Optional<Status> findByName(String name);
}
