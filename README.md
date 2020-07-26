# User-Access-Management System

This is a service for managing the access rights of users.There are 3 roles a user can possess.ADMIN is the supreme user.
READ_USER is the default role. He will be able to read contents of files which has access type ANY.WRITE_USER can create resources.
Resource has access types.Access type ANY means, anyone can access the file. ACCESS_ONLY means, only the users who has access to that resource can access the file.
ADMIN can access all the files. He can grant access on resources to users. And he can set the user role.

## Requirements:
* Maven-3.6.3
* JDK-1.8.0_251

## Build and Run instructions:
* create jar by `mvn clean install -DskipTests`
* Move to target folder
* Run program `java -jar user-management-1.0-SNAPSHOT.jar`


## Supported Commands:
* `LOGIN`                                                                                                              - To login with the creadentials
* `SIGN_UP`                                                                                                            - To sign up 
* `LIST`                                                                                                               - See all the resources
* `GET _**resourcename**_`                                                                                             - get a perticular resource by name
* `CREATE name=_**resourcename**_ path=_**resource_path**_ access_type=_**access type**_`                              - Create a resource. access_type can be ANY/ACCESS_ONLY
* `MODIFY _**resourcename**_ name=_**new_resourcename**_ path=_**{resource_path}**_ access_type=_**new access type**_` - modify a resource
* `SET **_username_** **_role_**`                                                                                      - set a role for a user, role can be READ_USER,WRITE_USER,ADMIN
* `GRANT _**resourcename**_ _**username**_`                                                                            - grant access to a perticular resource
* `SIGN_OUT`                                                                                                           - to sign_out of logged in session
* `QUIT`                                                                                                               - exit program

## Important Files:
* ResourceServiceImpl
* UserServiceImpl
* UserManagementApp
* AccessType
* Command
* Role

#### Admin Credentials :
username : superuser
password : password