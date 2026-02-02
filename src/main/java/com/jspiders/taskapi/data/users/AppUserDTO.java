package com.jspiders.taskapi.data.users;

import com.jspiders.taskapi.data.tasks.TaskDTO;

import java.util.List;

public class AppUserDTO {
    private Long userId;
    private String name;
    private String email;
    private String mobile;
    private boolean Active;
   private List<TaskDTO> taskDTOList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public List<TaskDTO> getTaskDTOList() {
        return taskDTOList;
    }

    public void setTaskDTOList(List<TaskDTO> taskDTOList) {
        this.taskDTOList = taskDTOList;
    }
}
