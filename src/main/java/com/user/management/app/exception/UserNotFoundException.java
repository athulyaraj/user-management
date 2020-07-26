package com.user.management.app.exception;
/** Thrown when referring to a user who does not exist in the system**/
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
