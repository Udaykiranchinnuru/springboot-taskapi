package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.tasks.Task;
import com.jspiders.taskapi.data.tasks.TaskDTO;
import com.jspiders.taskapi.data.tasks.TaskRepository;
import com.jspiders.taskapi.data.users.*;
import com.jspiders.taskapi.errors.DuplicateUserException;
import com.jspiders.taskapi.services.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl2 implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final ObjectMapper mapper;
    private final TaskRepository taskRepository;

    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest request) {

        log.info("createUser() called with {}", request);

        boolean exists = appUserRepository
                .existsByEmailOrMobile(request.getEmail(), request.getMobile());

        if (exists) {
            log.warn("Duplicate user detected: email={}, mobile={}",
                    request.getEmail(), request.getMobile());
            throw new DuplicateUserException("User with given email/mobile already exists");
        }

        AppUser appUser = mapper.convertValue(request, AppUser.class);
        appUser.setActive(true);

        AppUser savedUser = appUserRepository.save(appUser);

        CreateUserResponse response = new CreateUserResponse();
        response.setUserId(savedUser.getUserId());
        response.setMessage("User Created");

        log.info("User created successfully with id={}", savedUser.getUserId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    public ResponseEntity<AppUserDTO> getUserById(Long userId) {
        log.info("getUserById()");
        //find the user by userId
        AppUser appUser = appUserRepository.findById(userId).orElseThrow();

        //convert appuser to appuseDto
        AppUserDTO appUserDTO = mapper.convertValue(appUser, AppUserDTO.class);

        //find all the task of the user by userId

        List<Task> taskList = taskRepository.findByAppUserUserId(userId);
        List<TaskDTO> taskDtoList = new ArrayList<>();

        //convert task to taskdto
        for (Task task:taskList)
        {
            TaskDTO taskDTO = mapper.convertValue(task,TaskDTO.class);
            taskDtoList.add(taskDTO);
        }
        //set the taskDto list
        appUserDTO.setTaskDTOList(taskDtoList);
        //return response object
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(appUserDTO);
    }

    @Override
    public ResponseEntity<AppUserDTO> getUserByEmail(String email) {

        log.info("getUserByEmail() email={}", email);

        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AppUserDTO dto = mapper.convertValue(appUser, AppUserDTO.class);

        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {

        log.info("login() attempt for email={}", loginRequest.getEmail());

        boolean valid = appUserRepository
                .existsByEmailAndPassword(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                );

        if (!valid) {
            log.error("Invalid login attempt for email={}", loginRequest.getEmail());
            throw new IllegalArgumentException("Invalid username/password");
        }

        AppUser appUser = appUserRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LoginResponse response = mapper.convertValue(appUser, LoginResponse.class);
        response.setMesssage("Login success");

        log.info("Login successful for userId={}", appUser.getUserId());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<String> updateUserEmail(UpdateUserEmailRequest updateUserEmailRequest) {
        return null;
    }

    // --- Unused methods (safe placeholders) ---

    @Override
    public ResponseEntity<CreateUserResponse> createUserResponse(CreateUserRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateUser()
    {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteUser(String email, String mobile, String password) {
        return null;
    }

    @Override
    public ResponseEntity<List<AppUserDTO>> getAllUsers( Long userId) {
        return null;
    }


    @Override
    public ResponseEntity<String> updateUserEmail(Long userId, UpdateUserEmailRequest updateUserEmailRequest)
    {
        log.info("this is updateUserEmail()");
        //verify the user
        boolean isPresent = appUserRepository.existsById(userId);
        if(isPresent==false)
        {
            throw new IllegalArgumentException("Security ERROR : USERID is not VALID");
        }
        Optional<AppUser> appUserOptional =
                appUserRepository.findByEmailAndUserId(updateUserEmailRequest.getOldEmail()
                        ,updateUserEmailRequest.getUserId());

        if(appUserOptional.isEmpty())
        {
            throw new IllegalArgumentException("User with given email and userid NOT FOUND");
        }
        else {
            AppUser appUser = appUserOptional.get();
            appUser.setEmail(updateUserEmailRequest.getNewEmail());
            appUserRepository.save(appUser);
        }

        return ResponseEntity.ok("User email updated successfully");
    }


}
