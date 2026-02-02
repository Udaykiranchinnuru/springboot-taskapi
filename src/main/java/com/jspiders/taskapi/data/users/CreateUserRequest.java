package com.jspiders.taskapi.data.users;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CreateUserRequest {
    @Length(min = 3,max = 45,message = "name should be min-3 char and max char-45 only")
    @NotBlank(message = "name cannot be empty")
    private String name;

    @Email(message = "invalid email id format")
    @NotBlank( message = "email cannot be empty")
    private String email;

    @Length(min = 10,max = 10,message = "mobile number should be 10-digits only")
    @NotBlank(message = "mobile number is not empty")
    private  String mobile;

    @Length(min = 6,max = 10,message = "password should be min-6 and max-15 char only")
    @NotBlank(message = "password need to enter mandatory")
    private String password;

    private Boolean Active;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }
}
