package com.example.demo.core.notifications.application;

import com.example.demo.core.notifications.infrastructure.entity.Notification;
import com.example.demo.core.notifications.presentation.dto.NotificationRequest;

import java.util.List;

public interface NotificationService {
    Notification saveNotification(Long recipientId, Long senderId,
            NotificationRequest request);
    List<Notification> findAllUserNotifications();

}
