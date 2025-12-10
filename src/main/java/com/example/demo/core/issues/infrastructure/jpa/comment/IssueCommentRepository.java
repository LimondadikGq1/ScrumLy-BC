package com.example.demo.core.issues.infrastructure.jpa.comment;

import com.example.demo.core.issues.infrastructure.entity.comment.IssueComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueCommentRepository extends JpaRepository<IssueComment, Long> {
}
