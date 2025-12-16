package com.example.demo.core.issues.infrastructure.jpa;

import com.example.demo.core.issues.infrastructure.entity.Issue;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {

    Optional<Issue> findByName(String name);

    @Query("""
       SELECT i FROM Issue i
       INNER JOIN UserProjectRole upr on upr.project.id = :projectId
       WHERE 
       i.project.id = :projectId AND 
       upr.user.id = :userId AND 
       i.id = :issueId
""")
    Optional<Issue> findById(@Param("userId") Long userId,
                             @Param("projectId") Long projectId,
                             @Param("issueId") Long issueId);

}
