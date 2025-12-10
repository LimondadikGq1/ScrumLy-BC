package com.example.demo.core.issues.application.main.impl;

import com.example.demo.core.issues.infrastructure.entity.Issue;
import com.example.demo.core.issues.presentation.dto.requests.CreateIssueRequest;

public interface IssueService {

    Issue createIssue(Long userId, CreateIssueRequest request);

    Issue getIssueInfoById(Long issueId);

    void closeIssueById(Long issueId);
}
