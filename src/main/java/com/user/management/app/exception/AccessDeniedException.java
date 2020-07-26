package com.user.management.app.exception;
/**
 * Thrown when trying to access a resource or perform an action the user does not have permission to
 * **/
public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(){

    }

    public AccessDeniedException(String message){
        super(message);
    }


}
