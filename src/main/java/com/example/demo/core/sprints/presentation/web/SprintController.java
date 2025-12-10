package com.example.demo.core.sprints.presentation.web;

import com.example.demo.auth.user.UserDetails;
import com.example.demo.core.sprints.application.SprintService;
import com.example.demo.core.sprints.presentation.dto.responses.SprintClosedResponse;
import com.example.demo.core.sprints.presentation.dto.responses.SprintCreateResponse;
import com.example.demo.core.sprints.presentation.dto.responses.SprintFullDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.core.sprints.presentation.web.SprintControllerPaths.CLOSE_SPRINT;
import static com.example.demo.core.sprints.presentation.web.SprintControllerPaths.CREATE_SPRINT;
import static com.example.demo.core.sprints.presentation.web.SprintControllerPaths.GET_INFO_SPRINT;
import static com.example.demo.core.sprints.presentation.web.SprintControllerPaths.ID;
import static com.example.demo.core.sprints.presentation.web.SprintControllerPaths.SPRINT_BASE_PATH;
import static com.example.demo.core.sprints.presentation.web.SprintControllerPaths.V1;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = SPRINT_BASE_PATH)
public class SprintController {
    private final SprintService sprintService;


    @PostMapping(path = V1 + CREATE_SPRINT + ID)
    public ResponseEntity<SprintCreateResponse> createSprint(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long projectId
    ){
    return null;
    }

    @PostMapping(path = V1 + CLOSE_SPRINT + ID)
    public ResponseEntity<SprintClosedResponse> closeSprint(
            @PathVariable("id") Long sprintId
    ){
        return null;
    }

    @GetMapping(path = V1 + GET_INFO_SPRINT + ID)
    public ResponseEntity<SprintFullDataResponse> getSprintInfo(
            @PathVariable("id") Long sprintId
    ){
        return null;
    }


}
