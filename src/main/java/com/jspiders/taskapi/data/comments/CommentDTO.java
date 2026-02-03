package com.jspiders.taskapi.data.comments;

import com.jspiders.taskapi.data.users.AppUserDTO;
import lombok.Data;


@Data
public class CommentDTO
{
    private Long id;
    private String text;
    private AppUserDTO user;
    private String createdAt;
}
