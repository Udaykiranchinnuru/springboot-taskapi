package com.jspiders.taskapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
public class CommentsController
{
    @PostMapping
    ResponseEntity<String> addComment()
    {
        return null;
    }
    @GetMapping
    ResponseEntity<String> getComments(@PathVariable Long userId,@PathVariable Long taskId)
    {
        return null;
    }
}
