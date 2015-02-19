package com.microBiz.model;

import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.CreationEmail;
import org.slim3.datastore.CreationUser;
import org.slim3.datastore.ModificationDate;

import com.google.appengine.api.datastore.Key;

public abstract class MiCreatorBaseModel extends MiBaseModel {
    

    @Attribute(listener = CreationDate.class)
    protected Date createdAt;
    
    @Attribute(listener = CreationEmail.class)
    protected String createdEmail;

 

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedEmail() {
       
        return createdEmail;
      
    }

    public void setCreatedEmail(String createdEmail) {
        this.createdEmail = createdEmail.toLowerCase();
    }
    

    
    
}
