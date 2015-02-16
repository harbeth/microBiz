package com.microBiz.model;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

@Model
public class MiRoleAccessRight extends MiBaseModel {

 
    private String role;
    
    private String accessibleModule;
    
 


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
