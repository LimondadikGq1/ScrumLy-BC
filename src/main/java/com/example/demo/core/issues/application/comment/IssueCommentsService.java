package com.example.demo.core.issues.application.comment;

import com.example.demo.core.colaboration.presentation.dto.requests.CreateIssueCommentRequest;
import com.example.demo.core.colaboration.presentation.dto.requests.DeleteIssueCommentRequest;
import com.example.demo.core.colaboration.presentation.dto.responses.CreateIssueCommentResponse;

public interface IssueCommentsService {

    CreateIssueCommentResponse createComment(CreateIssueCommentRequest request);

    void deleteComment(DeleteIssueCommentRequest request);
}
