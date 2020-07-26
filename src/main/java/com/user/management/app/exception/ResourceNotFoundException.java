package com.user.management.app.exception;
/** Thrown when trying to access a non existent resource**/
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

}
