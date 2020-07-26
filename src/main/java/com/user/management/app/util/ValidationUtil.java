package com.user.management.app.util;

import com.user.management.app.entity.Resource;
import com.user.management.app.entity.User;
import com.user.management.app.enums.Command;
import com.user.management.app.exception.InvalidParamException;
import com.user.management.app.exception.OperationDeniedException;
import com.user.management.app.exception.ResourceNotFoundException;
import com.user.management.app.exception.UserNotFoundException;
import lombok.extern.java.Log;

import java.util.Map;

@Log
public class ValidationUtil {

    public static void isResourceAvailable(Resource resource){
        if(resource == null){
            log.info("Resource does not exist");
            throw new ResourceNotFoundException("Resource does not exist");
        }
    }

    public static void isUserAvailable(User user){
        if(user == null){
            log.info("User not found");
            throw new UserNotFoundException("user does not exists");
        }
    }

    public static void validateUsername(String username){
        if(CommonUtil.isEmpty(username)){
            log.info("Invalid username");
            throw new InvalidParamException("Invalid username");
        }
    }

    public static User validateUsername(String username,Map<String,User> userRepo,Command command){
        validateUsername(username);
        User user = userRepo.get(username);
        if(user != null && Command.SIGN_UP.equals(command)){
            log.info("Username already in use");
            throw new InvalidParamException("Invalid username");
        }
        if(user == null && Command.LOGIN.equals(command)){
            log.info("Invalid username");
            throw new InvalidParamException("Invalid username");
        }
        return user;
    }

    public static void validatePassword(String password,Command command, Map<String,User> userRepo,User user){
        if(CommonUtil.isEmpty(password)){
            log.info("Invalid password");
            throw new InvalidParamException("Invalid password");
        }
        if(Command.LOGIN.equals(command)){
            if (!user.getPassword().equals(password)) {
                log.info("Incorrect password");
                throw new InvalidParamException("Incorrect password");
            }
        }
    }

    /** Input validation for CREATE/MODIFY resource **/
    public static void validateResource(Resource resource,Map<String,Resource> resourceRepo){
        if(CommonUtil.isEmpty(resource.getResourceName()) || CommonUtil.isEmpty(resource.getUrl())){
            log.info("Invalid resourceName/path");
            throw new InvalidParamException("Invalid resourceName/path");
        }
        if(resourceRepo.containsKey(resource.getResourceName())){
            log.info("Resource exists");
            throw new OperationDeniedException("Resource exists");
        }
    }

}
