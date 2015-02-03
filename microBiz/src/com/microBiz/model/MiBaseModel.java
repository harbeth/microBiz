package com.microBiz.model;

import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.CreationUser;
import org.slim3.datastore.ModificationDate;

public abstract class MiBaseModel {
    
    @Attribute(listener = ModificationDate.class)
    protected Date updatedAt;
    
    @Attribute(listener = CreationDate.class)
    protected Date createdAt;
    

    
    
}
