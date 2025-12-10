package com.example.demo.core.issues.application.additinals;

import com.example.demo.core.issues.infrastructure.entity.additionals.Status;
import com.example.demo.core.issues.presentation.dto.requests.status.CreateStatusRequest;

public interface StatusService {
    Status create(CreateStatusRequest request);

    Status findById(Long statusId);

    Status findByName(String name);
}
