package com.user.management.app.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Roles a user can possess. if ADMIN, user can READ,WRITE,MODIFY and GRANT access to resources.
 * WRITE_USER will be able to read and write resources to system
 * READ_USER is the default mode
 **/
public enum Role {
    ADMIN(Arrays.asList(new ActionType[]{ActionType.READ,ActionType.WRITE,ActionType.GRANT,ActionType.MODIFY,ActionType.SET})),
    READ_USER(Arrays.asList(new ActionType[]{ActionType.READ})),
    WRITE_USER(Arrays.asList(new ActionType[]{ActionType.WRITE,ActionType.READ}));

    List<ActionType> actionTypes;
    Role(List<ActionType> actionTypes){
        this.actionTypes = actionTypes;
    }

    public List<ActionType> getActionTypes(){
        return this.actionTypes;
    }

}
