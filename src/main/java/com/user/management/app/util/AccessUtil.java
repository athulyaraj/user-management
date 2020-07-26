package com.user.management.app.util;

import com.user.management.app.entity.Resource;
import com.user.management.app.entity.User;
import com.user.management.app.entity.UserAction;
import com.user.management.app.enums.AccessType;
import com.user.management.app.enums.ActionType;
import com.user.management.app.enums.Role;
import com.user.management.app.exception.AccessDeniedException;
import lombok.extern.java.Log;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

@Log
public class AccessUtil {
    /**
     * Checking access for a perticlur ACCESS_ONLY file
     **/
    public static void checkAccess(User user, Resource resource, Map<String,List<UserAction>> resourceUserAccess,ActionType actionType){
        checkUserAccess(user);
        if(resource.getAccessType().equals(AccessType.ACCESS_ONLY)){
                List<UserAction> users = resourceUserAccess.get(resource.getResourceName());
                if(!CommonUtil.isEmpty(users) && !users.stream().filter(i->i.getUsername().equals(user.getUserName()) && checkAccess(i.getActionTypeList(),actionType)).findFirst().isPresent()){
                    log.info("Access Denied : User does not have access permission");
                    throw new AccessDeniedException("Access Denied");
            }
        }
    }

    /**
     * Checking whether the user has proper role set for operation
     * **/
    public static void checkUserAccess(User user){
        Role defaultRole = user.getDefaultRole();
        if(defaultRole == null){
            log.info("Access Denied : no proper access right exists for this user");
            throw new AccessDeniedException("Access Denied");
        }
    }

    /** Checking Access of a user**/
    public static void checkAccess(User user,ActionType actionType){
        checkUserAccess(user);
        Role defaultRole = user.getDefaultRole();
        if(!checkAccess(defaultRole.getActionTypes(),actionType)){
            log.info("User does not have permission for this operation");
            throw new AccessDeniedException("Access Denied");
        }
    }

    public static boolean checkAccess(List<ActionType> allowedActionTypes,ActionType actionType){
        return allowedActionTypes.stream().filter(i->i.equals(actionType)).findFirst().isPresent();
    }

}
