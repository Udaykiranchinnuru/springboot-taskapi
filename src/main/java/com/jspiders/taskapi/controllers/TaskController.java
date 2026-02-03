package com.jspiders.taskapi.controllers;


import com.jspiders.taskapi.data.tasks.AssignTagToTaskResponse;
import com.jspiders.taskapi.data.tasks.CreateTaskRequest;
import com.jspiders.taskapi.data.tasks.Task;
import com.jspiders.taskapi.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
@Slf4j

public class TaskController {
      private final TaskService taskService;

    @PostMapping
    ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest createTaskRequest)
    {
        log.info("inside createTask {}",createTaskRequest);
        return taskService.createTask(createTaskRequest);
    }
    @PostMapping("/{taskId}/tags/{tagId}")
    ResponseEntity<AssignTagToTaskResponse> assignTagToTask(@PathVariable Long taskId,
                                                            @PathVariable Long tagId){
        return taskService.addTagToTask(taskId,tagId);
    }

}
