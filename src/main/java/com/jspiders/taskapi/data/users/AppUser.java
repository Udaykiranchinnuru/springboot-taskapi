package com.jspiders.taskapi.data.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jspiders.taskapi.data.tasks.Task;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@ToString(exclude = "taskList")
@Table(name = "appusers")
public class AppUser {
    @Id
    @Column(name = "userid", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Mysql will create and manage ID
    private Long userId;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "mobile", nullable = false, unique = true, length = 10)
    private String mobile;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    @OneToMany(mappedBy = "appUser")
    @JsonIgnore
    private List<Task> taskList;

}

