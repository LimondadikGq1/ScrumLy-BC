package com.example.demo.core.issues.presentation.web;

import com.example.demo.auth.user.UserDetails;
import com.example.demo.core.issues.application.main.impl.IssueService;
import com.example.demo.core.issues.infrastructure.entity.Issue;
import com.example.demo.core.issues.presentation.dto.requests.CreateIssueRequest;
import com.example.demo.core.issues.presentation.dto.responses.issue.FullDataIssueResponse;
import com.example.demo.core.issues.presentation.dto.responses.status.ChangeStatusIssueResponse;
import com.example.demo.core.issues.presentation.dto.responses.type.ChangeTypeIssueResponse;
import com.example.demo.core.issues.presentation.mappers.issue.IssueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.core.issues.presentation.web.IssueControlerPaths.CHANGE;
import static com.example.demo.core.issues.presentation.web.IssueControlerPaths.CHANGE_ISSUE_DATA;
import static com.example.demo.core.issues.presentation.web.IssueControlerPaths.CHANGE_STATUS;
import static com.example.demo.core.issues.presentation.web.IssueControlerPaths.CHANGE_TYPE;
import static com.example.demo.core.issues.presentation.web.IssueControlerPaths.CREATE_ISSUE;
import static com.example.demo.core.issues.presentation.web.IssueControlerPaths.GET_INFO_ISSUE;
import static com.example.demo.core.issues.presentation.web.IssueControlerPaths.ID;
import static com.example.demo.core.issues.presentation.web.IssueControlerPaths.ISSUE_BASE_PATH;
import static com.example.demo.core.issues.presentation.web.IssueControlerPaths.V1;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ISSUE_BASE_PATH)
public class IssueController {

    private final IssueService issueService;

    private final IssueMapper issueMapper;

    @PostMapping(path = V1 + CREATE_ISSUE)
    public ResponseEntity<FullDataIssueResponse> createIssue(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CreateIssueRequest request
    ){
        Issue issue = issueService.createIssue(userDetails.getId(), request);
        FullDataIssueResponse response = issueMapper.toFullDataIssueResponse(issue);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path =  CHANGE + CHANGE_STATUS + ID)
    public ResponseEntity<ChangeStatusIssueResponse> changeStatus(
    ){
        return null;
    }

    @PostMapping(path =  CHANGE + CHANGE_TYPE + ID)
    public ResponseEntity<ChangeTypeIssueResponse> changeType(

    ){
        return null;
    }

    @PostMapping(path =  CHANGE + CHANGE_ISSUE_DATA)
    public ResponseEntity<FullDataIssueResponse> changeIssueData(

    ){
        return null;
    }

    @GetMapping(path =  GET_INFO_ISSUE + ID)
    public ResponseEntity<CreateIssueRequest> getIssueInfo(

    ){
        return null;
    }
}
