package com.microBiz.model;

import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;

import com.microBiz.MicroBizUtil;

public abstract class MiCreatorBaseModel extends MiBaseModel {
    
    private static final long serialVersionUID = 1L;

    @Attribute(listener = CreationDate.class)
    protected Date createdAt;
    
    @Attribute(listener = CreatorName.class)
    protected String creatorName;

 

    public Date getCreatedAt() {
        return createdAt;
    }
    
    public String getCreatedAtStr() {
        return MicroBizUtil.parseDateToStr(createdAt);
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    
    


}
