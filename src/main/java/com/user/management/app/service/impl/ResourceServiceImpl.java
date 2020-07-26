package com.user.management.app.service.impl;

import com.user.management.app.entity.Resource;
import com.user.management.app.entity.User;
import com.user.management.app.enums.AccessType;
import com.user.management.app.enums.Role;
import com.user.management.app.service.ResourceService;
import com.user.management.app.util.AccessUtil;
import com.user.management.app.util.CommonUtil;
import com.user.management.app.util.ValidationUtil;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
public class ResourceServiceImpl implements ResourceService {

    Map<String,Resource> resourceRepo = new HashMap<>();
    Map<String, List<String>> resourceUserAccess = new HashMap<>();
    public static ResourceServiceImpl resourceService;

    public static ResourceService getInstance(){
        if(resourceService == null){
            resourceService = new ResourceServiceImpl();
        }
        return resourceService;
    }
    @Override
    public Map<String, Resource> getResourceRepo() {
        return resourceRepo;
    }

    @Override
    public Map<String, List<String>> getResourceUserAccessRepo() {
        return resourceUserAccess;
    }

    @Override
    public void listResource() {
        StringBuffer buffer = new StringBuffer("");
        for(Map.Entry<String,Resource> resourceEntry : resourceRepo.entrySet()){
            buffer.append(resourceEntry.getKey()).append("\n");
        }
        log.info(buffer.toString());
    }

    @Override
    public void getResource(String resourceName, User user) {
        Resource resource = resourceRepo.get(resourceName);
        ValidationUtil.isResourceAvailable(resource);
        AccessUtil.checkAccess(user,resource,resourceUserAccess);
        log.info(resource.toString());
    }

    @Override
    public void createResource(Resource resource, User user) {
        ValidationUtil.validateResource(resource);
        AccessUtil.checkUserAccess(user);
        AccessUtil.checkAccess(user, Role.WRITE_USER);
        if(resource.getAccessType() == null){
            resource.setAccessType(AccessType.ANY);
        }
        resourceRepo.put(resource.getResourceName(), resource);
        if(resource.getAccessType().equals(AccessType.ACCESS_ONLY)){
            List<String> accessibleUsers = new ArrayList<>();
            accessibleUsers.add(user.getUserName());
            resourceUserAccess.put(resource.getResourceName(), accessibleUsers);
        }
        log.info("OK");
    }

    @Override
    public void modifyResource(String resourceName, User user, Resource r) {
        Resource resource = resourceRepo.get(resourceName);
        ValidationUtil.isResourceAvailable(resource);
        AccessUtil.checkAccess(user,resource,resourceUserAccess);
        if(r.getAccessType() != null){
            resource.setAccessType(r.getAccessType());
        }
        if(!CommonUtil.isEmpty(r.getResourceName())){
            List<String> users = resourceUserAccess.get(resource.getResourceName());
            resourceUserAccess.remove(resource.getResourceName());
            resourceRepo.remove(resource.getResourceName());
            resource.setResourceName(resourceName);
            resourceRepo.put(r.getResourceName(),resource);
            resourceUserAccess.put(r.getResourceName(),users);
        }
        if(!CommonUtil.isEmpty(r.getUrl())){
            resource.setUrl(r.getUrl());
        }
        log.info("OK");
    }


}
