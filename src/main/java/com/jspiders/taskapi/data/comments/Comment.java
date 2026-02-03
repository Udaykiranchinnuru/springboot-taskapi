package com.jspiders.taskapi.data.comments;

import com.jspiders.taskapi.data.tasks.Task;
import com.jspiders.taskapi.data.users.AppUser;
import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public void setUser(AppUser user) {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "text='" + text + '\'' +
                ", id=" + id +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
