package com.example.demo.core.issues.infrastructure.jpa;

import com.example.demo.core.issues.infrastructure.entity.Issue;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {

    Optional<Issue> findById(@NotNull Long issueId);

    Optional<Issue> findByName(String name);

}
