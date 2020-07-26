package com.user.management.app.enums;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ADMIN(Arrays.asList(new ActionType[]{ActionType.READ, ActionType.WRITE, ActionType.MODIFY, ActionType.GRANT})),
    READ_USER(Arrays.asList(new ActionType[]{ActionType.READ})),
    WRITE_USER(Arrays.asList(new ActionType[]{ActionType.READ, ActionType.WRITE}));
    List<ActionType> actionTypes;

    Role(List<ActionType> actionTypes){
        this.actionTypes = actionTypes;
    }
}
