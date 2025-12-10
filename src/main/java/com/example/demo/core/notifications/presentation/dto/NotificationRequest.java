package com.example.demo.core.notifications.presentation.dto;

import lombok.Builder;

@Builder
public record NotificationRequest(
        Long projectId,
        String message
) {
}
