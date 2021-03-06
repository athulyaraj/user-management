package com.user.management.app.util;

import com.user.management.app.UserManagementApp;
import com.user.management.app.entity.Resource;
import com.user.management.app.enums.AccessType;
import com.user.management.app.enums.ActionType;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Log
public class CommonUtil {

    public static boolean isEmpty(String s){
        if(s == null || s.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Collection c){
        if(c == null || c.isEmpty())
            return true;
        return false;
    }

    /** For reading from resources folder **/
    public static void getFileFromResources(String fileName){
        try {
            InputStream is = UserManagementApp.class.getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer banner = new StringBuffer("\n");
            while ((line = reader.readLine()) != null) {
                banner.append(line).append("\n");
            }
            log.info(banner.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** For creating resource from CREATE/MODIFY resource commands **/
    public static Resource createOrModifyResourceRequest(List<String> parts){
        Resource resource = new Resource();
        for(String string : parts){
            String[] params = string.split("=");
            if(params.length > 1){
                switch(params[0]){
                    case "name"         :   resource.setResourceName(params[1]);
                                            break;
                    case "path"         :   resource.setUrl(params[1]);
                                            break;
                    case "access_type"  :   resource.setAccessType(AccessType.valueOf(params[1]));
                                            break;
                    default             :   log.info("Unrecognizable param");
                                            break;
                }
            }
        }
        return resource;
    }

    public static List<ActionType> getActionTypesFromArgs(String params){
        List<String> actions = Arrays.asList(params.split(","));
        List<ActionType> actionTypes = new ArrayList<>();
        for(String action : actions){
            actionTypes.add(ActionType.valueOf(action));
        }
        return actionTypes;
    }
}
