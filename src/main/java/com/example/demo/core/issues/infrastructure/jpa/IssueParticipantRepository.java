package com.example.demo.core.issues.infrastructure.jpa;

import com.example.demo.core.issues.infrastructure.entity.participant.IssueParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueParticipantRepository  extends JpaRepository<IssueParticipant,Long> {
}
