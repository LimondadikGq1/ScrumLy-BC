package com.example.demo.core.notifications.infrastructure.entity;

import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.users.infrastructure.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import lombok.ToString;

import java.time.LocalDateTime;
@ToString
@Getter @Setter
@NoArgsConstructor
@Table
@Entity(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private  User recipient;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "message")
    private String message;

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Notification(User sender, User receipent, Project project, String message,
                        boolean isRead, LocalDateTime createdAt) {
        this.sender = sender;
        this.recipient = receipent;
        this.project = project;
        this.message = message;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }
}
