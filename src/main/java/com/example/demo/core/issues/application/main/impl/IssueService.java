package com.example.demo.core.issues.application.main.impl;

import com.example.demo.core.issues.infrastructure.entity.Issue;
import com.example.demo.core.issues.infrastructure.entity.additionals.Status;
import com.example.demo.core.issues.infrastructure.entity.additionals.Type;
import com.example.demo.core.issues.presentation.dto.requests.ChangeIssueDataRequest;
import com.example.demo.core.issues.presentation.dto.requests.CreateIssueRequest;
import com.example.demo.core.issues.presentation.dto.requests.IssueInfoRequest;
import com.example.demo.core.issues.presentation.dto.requests.status.ChangeStatusRequest;
import com.example.demo.core.issues.presentation.dto.requests.type.ChangeTypeRequest;

public interface IssueService {

    Issue createIssue(Long userId, CreateIssueRequest request);

    Issue getIssueInfoById(Long userId, IssueInfoRequest request);

    Status changeStatus(Long userId, ChangeStatusRequest request);

    Type changeType(Long userId, ChangeTypeRequest request);

    Issue changeData(Long userId, ChangeIssueDataRequest request);

}
