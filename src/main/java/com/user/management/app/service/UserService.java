package com.user.management.app.service;

import com.user.management.app.entity.User;
import com.user.management.app.enums.Role;

import java.io.BufferedReader;

public interface UserService {
    User performLogin(BufferedReader reader) throws Exception;
    User createUser(BufferedReader reader) throws Exception;
    User getUserContext(BufferedReader reader);
    void grantAccess(String resourceName,String userName,User user);
    void setUserRole(String username, User user, Role role);
}
