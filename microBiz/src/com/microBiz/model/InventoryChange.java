package com.microBiz.model;
import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;


@Model(kind = "InventoryHistory")
public class InventoryChange implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
    
    @Attribute(unindexed = true)
    private Double originalQty;
    
    @Attribute(unindexed = true)
    private Double changeQty;
    
    @Attribute(unindexed = true)
    private Double newQty;
    
    private Date changeDate;
    
    private String changeType;
    
    @Attribute(unindexed = true)
    private String notes;
    
    private ModelRef<Product> productRef = new ModelRef<Product>(Product.class);
    
    

    public ModelRef<Product> getProductRef() {
        return productRef;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Double getOriginalQty() {
        return originalQty;
    }

    public void setOriginalQty(Double originalQty) {
        this.originalQty = originalQty;
    }

    public Double getChangeQty() {
        return changeQty;
    }

    public void setChangeQty(Double changeQty) {
        this.changeQty = changeQty;
    }

    public Double getNewQty() {
        return newQty;
    }

    public void setNewQty(Double newQty) {
        this.newQty = newQty;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



    
}
