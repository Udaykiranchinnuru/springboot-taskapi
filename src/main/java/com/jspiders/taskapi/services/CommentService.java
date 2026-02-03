package com.jspiders.taskapi.services;

import com.jspiders.taskapi.data.comments.CommentRequest;
import com.jspiders.taskapi.data.comments.CommentResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    CommentResponse addComment(Long taskId, CommentRequest request);

    ResponseEntity<String>  getCommentsByUserAndTask(Long userId, Long taskId);
}
