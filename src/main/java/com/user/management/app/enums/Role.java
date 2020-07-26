package com.user.management.app.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Roles a user can possess. if ADMIN, user can READ,WRITE,MODIFY and GRANT access to resources.
 * WRITE_USER will be able to read and write resources to system
 * READ_USER is the default mode
 **/
public enum Role {
    ADMIN,
    READ_USER,
    WRITE_USER;

}
