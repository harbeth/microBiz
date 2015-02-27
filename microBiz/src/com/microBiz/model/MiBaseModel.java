package com.microBiz.model;

import java.io.Serializable;

import org.slim3.datastore.Attribute;

import com.google.appengine.api.datastore.Key;
import com.microBiz.PropertyHelper;

public abstract class MiBaseModel implements Serializable {
    
    protected static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    protected Key key;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    protected String getLable(Integer code){
        return PropertyHelper.getInstance().getLable(code);
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
