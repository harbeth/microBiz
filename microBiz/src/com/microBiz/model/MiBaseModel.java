package com.microBiz.model;

import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.CreationEmail;
import org.slim3.datastore.CreationUser;
import org.slim3.datastore.ModificationDate;

import com.google.appengine.api.datastore.Key;

public abstract class MiBaseModel {
    
    protected static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    protected Key key;
    @Attribute(listener = CreationDate.class)
    protected Date createdAt;
    
    @Attribute(listener = CreationEmail.class)
    protected String createdEmail;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

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
        this.createdEmail = createdEmail;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MiBaseModel other = (MiBaseModel) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
    
    
}
