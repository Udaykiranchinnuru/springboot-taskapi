package com.jspiders.taskapi.data.comments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>
{
    List<Comment> findByTask_TaskId(Long taskId);
}
