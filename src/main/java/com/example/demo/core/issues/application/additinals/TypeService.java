package com.example.demo.core.issues.application.additinals;

import com.example.demo.core.issues.infrastructure.entity.additionals.Type;
import com.example.demo.core.issues.presentation.dto.requests.status.CreateStatusRequest;

public interface TypeService {
    Type create(CreateStatusRequest request);

    Type findById(Long typeId);

    Type findByName(String name);
}
