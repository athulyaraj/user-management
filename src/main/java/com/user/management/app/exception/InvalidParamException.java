package com.user.management.app.exception;

public class InvalidParamException extends RuntimeException {

    public InvalidParamException(){
        super();
    }

    public InvalidParamException(String message){
        super(message);
    }
}
