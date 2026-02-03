package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.tags.TagRepository;
import com.jspiders.taskapi.data.tags.Tags;
import com.jspiders.taskapi.data.tasks.*;
import com.jspiders.taskapi.data.users.AppUserRepository;
import com.jspiders.taskapi.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final ObjectMapper mapper;
    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;
    private final TagRepository tagRepository;

    @Override
    public ResponseEntity<Task> createTask(CreateTaskRequest createTaskRequest)
    {
        log.info("inside createTask {}",createTaskRequest);

        //validate the userId if not present Throw NoSuchElementFoundException
        appUserRepository.findById(createTaskRequest.getUserId()).orElseThrow();


        //Convert createTaskRequest to Task Entity
        Task task=mapper.convertValue(createTaskRequest,Task.class);


        //set created and updated Dates

        task.setCreatedAt(LocalDate.now().toString());
        task.setUpdateAt(LocalDate.now().toString());

        //save the task to db
        Task savedTask=taskRepository.save(task);


        //return the response with savedTask
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);

    }

    @Override
    public ResponseEntity<List<Task>> getAllTasks() {
        return null;
    }

    @Override
    public ResponseEntity<Task> getTaskByID(Long taskId) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateTask(UpdateTaskRequest updateTaskRequest) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteTaskByID(Long taskId) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<AssignTagToTaskResponse> addTagToTask(Long taskId, Long tagId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        log.info("task {} ",task);
        Set<Tags> tags;

        tags = task.getTags();

        Tags tag = tagRepository.findById(tagId).orElseThrow();

        tags.add(tag);
        task.setTags(tags);

        taskRepository.save(task);

        AssignTagToTaskResponse response = new AssignTagToTaskResponse();
        response.setTaskId(task.getTaskId());
        response.setTitle(task.getTitle());
        response.setTags(task.getTags());

        return ResponseEntity.ok(response);
    }
}
