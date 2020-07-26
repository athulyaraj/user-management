package com.user.management.app.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(){

    }

    public AccessDeniedException(String message){
        super(message);
    }


}
