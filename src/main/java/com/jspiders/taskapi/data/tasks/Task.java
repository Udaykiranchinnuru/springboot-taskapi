package com.jspiders.taskapi.data.tasks;

import com.jspiders.taskapi.data.users.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class Task
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "taskId",nullable = false)
    private Long taskId;

    @Column(name = "title",nullable = false)
    private String title;



    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "status",nullable = false)
    private String status;

    @Column(name = "createdAt",nullable = false)
    private String createdAt;

    @Column(name = "updatedAt",nullable = false)
    private String updateAt;

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", taskId=" + taskId +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "userId")

    private AppUser appUser;

}
