package com.user.management.app.exception;
/** Thrown when invalid arguments are passed in with commands**/
public class InvalidParamException extends RuntimeException {

    public InvalidParamException(){
        super();
    }

    public InvalidParamException(String message){
        super(message);
    }
}
