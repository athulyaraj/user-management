package com.user.management.app.service;

import com.user.management.app.entity.Resource;
import com.user.management.app.entity.User;

import java.util.List;
import java.util.Map;
/** Managing all resource specific operations**/
public interface ResourceService {
    Map<String, Resource> getResourceRepo();
    Map<String, List<String>> getResourceUserAccessRepo();
    void listResource();
    void getResource(String resourceName, User user);
    void createResource(Resource resource ,User user);
    void modifyResource(String resourceName,User user,Resource r);
}
