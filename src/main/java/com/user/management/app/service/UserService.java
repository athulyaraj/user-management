package com.user.management.app.service;

import com.user.management.app.entity.User;
import com.user.management.app.enums.ActionType;
import com.user.management.app.enums.Role;

import java.io.BufferedReader;
import java.util.List;

/** Managing all user specific operations **/
public interface UserService {
    User performLogin(BufferedReader reader) throws Exception;
    User createUser(BufferedReader reader) throws Exception;
    User getUserContext(BufferedReader reader);
    void grantAccess(String resourceName, String userName, List<ActionType> actionTypeList, User user);
    void setUserRole(String username, User user, Role role);
}
