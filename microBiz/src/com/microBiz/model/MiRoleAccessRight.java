package com.microBiz.model;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

@Model
public class MiRoleAccessRight {

    @Attribute(primaryKey = true)
    private Key key;
    
    private String role;
    
    private String accessibleModule;
    
 

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccessibleModule() {
        return accessibleModule;
    }

    public void setAccessibleModule(String accessibleModule) {
        this.accessibleModule = accessibleModule;
    }

 
    
}
