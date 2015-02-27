package com.microBiz.model;

import org.slim3.datastore.Model;

@Model
public class Supplier extends MiBaseModel{

    private static final long serialVersionUID = 1L;

    private String name;
    
    private String active;
    
 
    public Supplier(){
        active="on";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }

 
}
