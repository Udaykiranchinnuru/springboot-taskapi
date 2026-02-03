package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.comments.CommentRequest;
import com.jspiders.taskapi.data.comments.CommentResponse;
import com.jspiders.taskapi.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentsController
{
    private  final CommentService commentService;
    @PostMapping
    CommentResponse addComment(@PathVariable Long taskId ,@RequestBody CommentRequest request )
    {
        log.info("addComment() text ");

        CommentResponse response = commentService.addComment(taskId, request);
        return response;
    }
    @GetMapping("/{userId}/{taskId}")
    ResponseEntity<String> getComments(@PathVariable Long userId,@PathVariable Long taskId)
    {
        log.info("getComments() userId={}, taskId={}", userId, taskId);

        ResponseEntity<String> response =
                commentService.getCommentsByUserAndTask(userId, taskId);

        return response;
    }
}
