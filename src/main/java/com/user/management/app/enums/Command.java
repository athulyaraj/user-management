package com.user.management.app.enums;
/**
 * List of commands we support
 * To sign-in, type:
 * LOGIN
 *
 * To sign-up, type:
 * SIGN_UP
 *
 * To List All the resources, use :
 * LIST
 *
 * To get a perticular resource by name :
 * GET resourcename
 *
 * To create a resource (ACCESS_TYPE can have ANY/ACCESS_ONLY as values, it is optional) :
 * CREATE name={new name} path={resource_path} access_type={new access type}
 *
 * To modify a resource :
 * MODIFY {resource_name} name={new name} path={resource_path} access_type={new access type}
 *
 * To get a resource :
 * GET resourcename
 *
 * To set role for a user, login as admin and use this command:
 * SET username
 *
 * To grant access to ACCESS_ONLY files, login as admin and use this command :
 * GRANT resourcename username
 *
 * To sign-out and login as different user:
 * SIGN_OUT
 *
 * To exit program,type:
 * QUIT
 **/
public enum Command {
    LOGIN,SIGN_UP,GET,CREATE, MODIFY,GRANT,LIST,SET,QUIT,SIGN_OUT;
}
