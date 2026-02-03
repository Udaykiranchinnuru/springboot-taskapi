package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.comments.*;
import com.jspiders.taskapi.data.tasks.Task;
import com.jspiders.taskapi.data.tasks.TaskRepository;
import com.jspiders.taskapi.data.users.AppUser;
import com.jspiders.taskapi.data.users.AppUserRepository;
import com.jspiders.taskapi.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService
{
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final AppUserRepository userRepository;
    @Override
    public CommentResponse addComment(Long taskId, CommentRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));


        AppUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setTask(task);
        comment.setUser(user);
        comment.setCreatedAt(LocalDate.now().toString());

        Comment savedComment = commentRepository.save(comment);

        // Mapping Entity → Response DTO
        CommentResponse response = new CommentResponse();
        response.setId(savedComment.getId());
        response.setText(savedComment.getText());
        response.setCreatedAt(savedComment.getCreatedAt());

        CommentUserResponse userResponse = new CommentUserResponse();
        userResponse.setId(user.getUserId());
        userResponse.setName(user.getName());
        response.setUser(userResponse);

        return response;

    }

    @Override
    public ResponseEntity<String> getCommentsByUserAndTask(Long userId, Long taskId) {
        return null;
    }
}
