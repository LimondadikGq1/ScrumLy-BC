/*
package com.example.demo.notifications.application.impl;

import com.example.demo.global.constants.ExceptionKeys;
import com.example.demo.notifications.application.NotificationService;
import com.example.demo.notifications.infrastructure.entity.Notification;
import com.example.demo.notifications.infrastructure.jpa.NotificationRepository;
import com.example.demo.notifications.presentation.dto.NotificationRequest;
import com.example.demo.projects.infrastructure.entity.Project;
import com.example.demo.projects.infrastructure.jpa.ProjectRepository;
import com.example.demo.users.exceptions.UserNotFoundException;
import com.example.demo.users.infrastructure.entity.User;
import com.example.demo.users.infrastructure.jpa.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final NotificationRepository notificationRepository;
    @Override
    public Notification saveNotification(
            Long recipientId,
            Long senderId,
            NotificationRequest request
            ) {
        User sender = userRepository.findById();
        User recipient = userRepository.findById(recipientId);
        Project project = projectRepository.findById(request.projectId());
        Notification notification = Notification.builder()
                .isRead(false)
                .sender(sender)
                .receipent(recipient)
                .message(request.message())
                .createdAt(LocalDateTime.now())
                .build();
        Notification savedNotification  =
                notificationRepository.save(notification);
        return savedNotification;
    }

    @Override
    public List<Notification> finaAllUserNotifications() {
        return List.of();
    }


}
*/
