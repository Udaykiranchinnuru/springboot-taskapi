package com.jspiders.taskapi.data.users;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateUserEmailRequest
{
    @NotBlank(message = "new email cant be empty")
    private String newEmail;

    @NotBlank(message = "old email cant be empty")
    private String oldEmail;

    @NotNull(message = "userId cant be empty")
    private String userId;

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
