package com.microBiz.model;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;


@Model(kind = "InventoryChange")
public class InventoryChange extends MiCreatorBaseModel {
    
    private static final long serialVersionUID = 1L;

    @Attribute(unindexed = true)
    private Double originalQty;
    
    @Attribute(unindexed = true)
    private Double changeQty;
    
    @Attribute(unindexed = true)
    private Double newQty;
    

    //this is for user input notes
    @Attribute(unindexed = true)
    private String notes;
    
 
    
    private ModelRef<Product> productRef = new ModelRef<Product>(Product.class);
    
    

    public ModelRef<Product> getProductRef() {
        return productRef;
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


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

 

    
}
