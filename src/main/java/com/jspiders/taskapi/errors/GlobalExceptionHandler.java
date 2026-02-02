package com.jspiders.taskapi.errors;


import com.jspiders.taskapi.controllers.UserController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionHandler
{
    private final Logger logger= LoggerFactory.getLogger(UserController.class);
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> arithmaticException(ArithmeticException ex)
    {
        System.out.println("handling Arithmatic exception");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nulpointerException(NullPointerException nx)
    {
        System.out.println("handling nullpointer exception");
        nx.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
    }
    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<String> invalidNameException(InvalidNameException nx)
    {
        System.out.println("handling InvalidNameException ");
        nx.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("name cannot be blank/empty");
    }
    @ExceptionHandler(InvalidMobileException.class)
    public ResponseEntity<String> invalidMobileexception(InvalidMobileException mx)
    {
        System.out.println("handling InvalidNameException ");
        mx.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("name cannot be blank/empty");
    }

    @ExceptionHandler(InvalidGmailException.class)
    public ResponseEntity<String> invalidGmailException(InvalidGmailException gx)
    {
        System.out.println("handling InvalidNameException ");
        gx.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("name cannot be blank/empty");
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> invalidPasswordException(InvalidPasswordException px)
    {
        System.out.println("handling InvalidNameException ");
        px.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("name cannot be blank/empty");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException mx)
    {
        Map<String,String> errorMap=new HashMap<>();
        List<FieldError> fieldErrors = mx.getFieldErrors();

      for(FieldError fieldError: fieldErrors)
      {
        String field = fieldError.getField();
        String errorMessage = fieldError.getDefaultMessage();
          errorMap.put(field,errorMessage);
       }
        logger.error("Validation Error : {}",errorMap);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }



}
