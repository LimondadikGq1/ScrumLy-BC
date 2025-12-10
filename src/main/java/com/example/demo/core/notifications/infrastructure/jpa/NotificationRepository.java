package com.example.demo.core.notifications.infrastructure.jpa;

import com.example.demo.core.notifications.infrastructure.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository  extends JpaRepository<Notification,Long> {
}
