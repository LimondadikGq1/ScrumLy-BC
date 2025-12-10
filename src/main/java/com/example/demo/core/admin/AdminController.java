package com.example.demo.core.admin;

import com.example.demo.core.issues.infrastructure.entity.additionals.Status;
import com.example.demo.core.issues.infrastructure.entity.additionals.Type;
import com.example.demo.core.issues.infrastructure.jpa.additionals.StatusRepository;
import com.example.demo.core.issues.infrastructure.jpa.additionals.TypeRepository;
import com.example.demo.core.issues.presentation.dto.requests.status.CreateStatusRequest;
import com.example.demo.core.issues.presentation.dto.requests.type.CreateTypeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminController {

    private final StatusRepository statusRepository;
    private final TypeRepository typeRepository;

    @PostMapping("/admin/status/")
    public ResponseEntity<Status> createStatus(
            @RequestBody CreateStatusRequest request
            ){
        Status status = Status.builder()
                .is_default(true)
                .name(request.name())
                .build();
        return ResponseEntity.ok(statusRepository.save(status));
    }
    @PostMapping("/admin/type/")
    public ResponseEntity<Type> createType(
            @RequestBody CreateTypeRequest request
    ){
        Type type = Type.builder()
                .is_default(true)
                .name(request.name())
                .build();

        return ResponseEntity.ok(typeRepository.save(type));
    }
}
