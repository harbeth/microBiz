package com.microBiz.model;

import org.slim3.datastore.Model;

@Model
public class MiRoleAccessRight extends MiBaseModel {

    private static final long serialVersionUID = 1L;

    private String miRole;
    
    private String accessibleModule;
    
 

    public String getMiRole() {
        return miRole;
    }

    public void setMiRole(String miRole) {
        this.miRole = miRole;
    }

    public String getAccessibleModule() {
        return accessibleModule;
    }

    public void setAccessibleModule(String accessibleModule) {
        this.accessibleModule = accessibleModule;
    }

 
    
}
