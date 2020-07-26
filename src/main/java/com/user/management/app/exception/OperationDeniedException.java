package com.user.management.app.exception;

public class OperationDeniedException extends RuntimeException {

    public OperationDeniedException(){
        super();
    }

    public OperationDeniedException(String message){
        super(message);
    }
}
