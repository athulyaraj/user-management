package com.user.management.app;

import com.user.management.app.entity.Resource;
import com.user.management.app.entity.User;
import com.user.management.app.enums.AccessType;
import com.user.management.app.enums.Command;
import com.user.management.app.enums.Role;
import com.user.management.app.service.ResourceService;
import com.user.management.app.service.UserService;
import com.user.management.app.service.impl.ResourceServiceImpl;
import com.user.management.app.service.impl.UserServiceImpl;
import com.user.management.app.util.CommonUtil;
import lombok.extern.java.Log;

import java.io.*;
import java.util.*;

import static com.user.management.app.enums.Command.*;

@Log
public class UserManagementApp {

    static UserService userService;
    static ResourceService resourceService;

    public static void main(String...args){
        CommonUtil.getFileFromResources("/banner.txt");
        String command = "";
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        userService = UserServiceImpl.getInstance();
        resourceService = ResourceServiceImpl.getInstance();
        User user = userService.getUserContext(reader);
        Command c = QUIT;
        do{
            try {
                log.info(">");
                command = reader.readLine();
                List<String> parts = Arrays.asList(command.split("\\s+"));
                c = Command.valueOf(parts.get(0));
                user = perform(c,parts,user,reader);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }while(!c.equals(QUIT));
    }

    public static User perform(Command c,List<String> parts,User user,BufferedReader reader){
        try{
            Resource resource = null;
            if(c == null){
                log.info("Command not recognized");
                return user;
            }
            switch(c){
                case LIST       :   resourceService.listResource();
                                    break;
                case GET        :   resourceService.getResource(parts.get(1),user);
                                    break;
                case CREATE     :   resource = CommonUtil.createOrModifyResourceRequest(parts);
                                    resourceService.createResource(resource,user);
                                    break;
                case MODIFY     :   resource = CommonUtil.createOrModifyResourceRequest(parts);
                                    resourceService.modifyResource(parts.get(1),user,resource);
                                    break;
                case GRANT      :   userService.grantAccess(parts.get(1),parts.get(2),user);
                                    break;
                case SET        :   userService.setUserRole(parts.get(1),user,Role.valueOf(parts.get(2)));
                                    break;
                case SIGN_OUT   :   user = userService.getUserContext(reader);
                                    break;
                case QUIT       :   log.info("Good bye!");
                                    break;
                default         :   log.info("Unknown command");
                                    break;
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return user;
    }
}
