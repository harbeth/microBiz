package com.microBiz.model;
import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;

// is child of product
@Model(kind = "prdRatio")
public class PrdRatio extends MiBaseModel {
    
 
    
    @Attribute(unindexed = true)
    private Double ratio;

    
    @Attribute(unindexed = true)
    private String desc;
    

    
    public Double getRatio() {
        return ratio;
    }
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
 
    
}
