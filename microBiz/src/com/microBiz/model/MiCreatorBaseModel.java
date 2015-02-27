package com.microBiz.model;

import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.CreationEmail;

import com.microBiz.MicroBizUtil;

public abstract class MiCreatorBaseModel extends MiBaseModel {
    
    private static final long serialVersionUID = 1L;

    @Attribute(listener = CreationDate.class)
    protected Date createdAt;
    
    @Attribute(listener = CreationEmail.class)
    protected String createdEmail;

 

    public Date getCreatedAt() {
        return createdAt;
    }
    
    public String getCreatedAtStr() {
        return MicroBizUtil.parseDateToStr(createdAt);
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedEmail() {
       
        return createdEmail;
      
    }
    
    public String getCreatorName() {
        
        return createdEmail;
      
    }

    public void setCreatedEmail(String createdEmail) {
        if(createdEmail!=null){
            this.createdEmail = createdEmail.toLowerCase();
        }else{
            this.createdEmail = createdEmail;
        }
        
    }
    

    
    
}
