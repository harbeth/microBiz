package com.microBiz.model;

import org.slim3.datastore.AttributeListener;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.microBiz.service.MiUserService;

public class CreatorName implements AttributeListener<String> {
    public String prePut(String value) {
        if (value != null) {
            return value;
        }
        MiUserService miUserService = new MiUserService();
        UserService us = UserServiceFactory.getUserService();
        User user = us.getCurrentUser();
        if (user != null) {
            return miUserService.getUserByEmail(user.getEmail()).getName();
        }
        return null;
    }
}
