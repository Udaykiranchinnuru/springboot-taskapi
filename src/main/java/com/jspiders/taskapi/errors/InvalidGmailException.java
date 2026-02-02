package com.jspiders.taskapi.errors;

public class InvalidGmailException extends  RuntimeException{
    public InvalidGmailException(String message){
        super(message);
    }

}
