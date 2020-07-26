package com.user.management.app.util;

import com.user.management.app.entity.Resource;
import com.user.management.app.entity.User;
import com.user.management.app.enums.AccessType;
import com.user.management.app.enums.Role;
import com.user.management.app.exception.AccessDeniedException;
import lombok.extern.java.Log;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

@Log
public class AccessUtil {

    public static boolean isAdmin(User user){
        return user.getDefaultRole() != null && user.getDefaultRole().equals(Role.ADMIN);
    }
    public static void checkAccess(User user, Resource resource, Map<String,List<String>> resourceUserAccess){
        if(resource.getAccessType().equals(AccessType.ACCESS_ONLY)){
            if(!AccessUtil.isAdmin(user)){
                List<String> users = resourceUserAccess.get(resource.getResourceName());
                if(!users.stream().filter(i->i.equals(user.getUserName())).findFirst().isPresent()){
                    log.info("Access Denied : User does not have access permission");
                    throw new AccessDeniedException("Access Denied");
                }
            }
        }
    }

    public static void checkUserAccess(User user){
        Role defaultRole = user.getDefaultRole();
        if(defaultRole == null){
            log.info("Access Denied : no proper access right exists for this user");
            throw new AccessDeniedException("Access Denied");
        }
    }

    public static void checkAccess(User user,Role role){
        if(!AccessUtil.isAdmin(user) && !user.getDefaultRole().equals(Role.WRITE_USER)){
            log.info("User does not have permission for this operation");
            throw new AccessDeniedException("Access Denied");
        }
    }

    public static void checkAdminAccess(User user){
        if(!AccessUtil.isAdmin(user)){
            log.info("User does not have permission for this operation");
            throw new AccessDeniedException("Access Denied");
        }
    }

}
