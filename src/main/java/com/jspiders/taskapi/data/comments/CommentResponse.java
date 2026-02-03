package com.jspiders.taskapi.data.comments;


import lombok.Data;

@Data
public class CommentResponse
{
    private Long id;
    private String text;
    private CommentUserResponse user;
    private String createdAt;

    public void setUser(CommentUserResponse userResponse) {

    }
}
