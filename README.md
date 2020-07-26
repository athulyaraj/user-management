# User-Access-Management System

Requirements:
* Maven-3.6.3
* JDK-1.8.0_251

Build and Run instructions:
* create jar by `mvn clean install -DskipTests`
* Move to target folder
* Run program `java -jar user-management-1.0-SNAPSHOT.jar`


Supported Commands:
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