package com.example.demo.core.issues.infrastructure.entity.participant;

import com.example.demo.core.issues.infrastructure.entity.Issue;
import com.example.demo.core.sprints.infrastructure.entity.enums.IssueRole;
import com.example.demo.core.users.infrastructure.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "issue_participants")
public class IssueParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private IssueRole role;

    @CreatedDate
    @Column(name = "joined_at")
    private LocalDateTime joined_at;

    @Builder
    public IssueParticipant(Issue issue, User user, IssueRole role) {
        this.issue = issue;
        this.user = user;
        this.role = role;
    }
}
