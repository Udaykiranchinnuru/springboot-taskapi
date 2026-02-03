package com.jspiders.taskapi.data.comments;

import lombok.Data;

@Data
public class CommentRequest {
    private String text;
    private Long userId;

}
