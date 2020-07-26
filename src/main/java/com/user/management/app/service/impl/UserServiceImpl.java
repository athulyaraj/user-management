package com.user.management.app.service.impl;

import com.user.management.app.entity.Resource;
import com.user.management.app.entity.User;
import com.user.management.app.enums.AccessType;
import com.user.management.app.enums.Command;
import com.user.management.app.enums.Role;
import com.user.management.app.service.ResourceService;
import com.user.management.app.service.UserService;
import com.user.management.app.util.AccessUtil;
import com.user.management.app.util.CommonUtil;
import com.user.management.app.util.ValidationUtil;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.user.management.app.enums.Command.*;
import static com.user.management.app.enums.Command.SIGN_UP;

@Log
public class UserServiceImpl implements UserService {

    static Map<String, User> userRepo = new HashMap<String,User>();
    static ResourceService resourceService = ResourceServiceImpl.getInstance();
    private static UserService userService;

    public static UserService getInstance(){
        if(userService == null){
            userService = new UserServiceImpl();
            User superUser = User.builder()
                        .userName("superuser")
                        .password("password")
                        .defaultRole(Role.ADMIN)
                        .build();
            userRepo.put(superUser.getUserName(),superUser);
        }
        return userService;
    }

    @Override
    public User performLogin(BufferedReader reader) throws Exception {
        User user = null;
        try {
            log.info("Enter username");
            String userName = reader.readLine();
            log.info("Enter password");
            String password = reader.readLine();
            user = ValidationUtil.validateUsername(userName, userRepo, LOGIN);
            ValidationUtil.validatePassword(password, LOGIN, userRepo, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        log.info("OK");
        return user;
    }

    @Override
    public User createUser(BufferedReader reader) throws Exception{
        User user = null;

        try {
            log.info("Enter username");
            String username = reader.readLine();
            ValidationUtil.validateUsername(username, userRepo, SIGN_UP);
            log.info("Enter password");
            String password = reader.readLine();
            ValidationUtil.validatePassword(password, SIGN_UP, userRepo, null);
            user = User.builder()
                    .userName(username)
                    .password(password)
                    .isAdmin(false)
                    .build();
            userRepo.put(username, user);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
        log.info("OK");
        return user;
    }

    @Override
    public User getUserContext(BufferedReader reader) {
        log.info("Please enter Login/Signup to continue");
        User user = null;
        Boolean repeat = false;
        do {
            try {
                String command = reader.readLine();
                Command c = Command.valueOf(command);
                if (c == null) {
                    log.info("Command not recognized");
                }
                switch (c) {
                    case LOGIN:
                        user = performLogin(reader);
                        break;
                    case SIGN_UP:
                        user = createUser(reader);
                        break;
                    default:
                        log.info("Unrecognizable command");
                        break;
                }
                repeat = false;
                return user;
            } catch (Exception ex) {
                System.out.println(ex);
                repeat = true;
            }
        }while(repeat);
        return user;
    }

    @Override
    public void grantAccess(String resourceName, String userName, User user) {
        AccessUtil.checkAdminAccess(user);
        Resource resource = resourceService.getResourceRepo().get(resourceName);
        ValidationUtil.isResourceAvailable(resource);
        if(resource.getAccessType().equals(AccessType.ACCESS_ONLY)){
            List<String> userAccess = resourceService.getResourceUserAccessRepo().get(resourceName);
            if(CommonUtil.isEmpty(userAccess)){
                userAccess = new ArrayList<>();
            }
            userAccess.add(userName);
            resourceService.getResourceUserAccessRepo().put(resourceName,userAccess);
        }
        log.info("OK");
    }

    @Override
    public void setUserRole(String username, User user, Role role) {
        AccessUtil.checkAdminAccess(user);
        User u = userRepo.get(username);
        ValidationUtil.isUserAvailable(user);
        u.setDefaultRole(role);
        log.info("OK");
    }
}
