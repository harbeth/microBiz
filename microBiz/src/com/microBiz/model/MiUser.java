package com.microBiz.model;

import org.slim3.datastore.Model;

@Model
public class MiUser extends MiBaseModel{

    private static final long serialVersionUID = 1L;

    private String name;
    
    private String email;
    
    private String role;
    
    private String active;
    
    private String phone;
    
    // sales commission rate, installer hourly rate
    private Double rate;

 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
    
    
}
