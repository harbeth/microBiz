package com.microBiz.model;
import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;


@Model(kind = "prd")
public class Product implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
    
  
    // selling, only displayed for quotation and invoice, raw material, only displayed for job report, both
    private String type;
    
    @Attribute(unindexed = true)
    private String desc;
    
    private String model;
    
    private String supplier;
    
    @Attribute(unindexed = true)
    private String sellingUnit;
    
    @Attribute(unindexed = true)
    private String purchaseUnit;
    
    @Attribute(unindexed = true)
    private Double sellingRate;
    
    @Attribute(unindexed = true)
    private Double currentQty;
    
    @Attribute(unindexed = true)
    private Double purchaseRate;
    
    @Attribute(unindexed = true)
    private String consumeReportUnit;

    // many to one
    @Attribute(persistent = false)
    private InverseModelListRef<PrdRatio, Product> prdRatioListRef = new InverseModelListRef<PrdRatio, Product>(PrdRatio.class, "prodcutRef", this);
  
    public InverseModelListRef<PrdRatio, Product> getPrdRatioListRef() {
        return prdRatioListRef;
    }
    
    @Attribute(unindexed = true)
    private String active;
    
 
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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
        Product other = (Product) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSellingUnit() {
        return sellingUnit;
    }

    public void setSellingUnit(String sellingUnit) {
        this.sellingUnit = sellingUnit;
    }

    public String getPurchaseUnit() {
        return purchaseUnit;
    }

    public void setPurchaseUnit(String purchaseUnit) {
        this.purchaseUnit = purchaseUnit;
    }

    public Double getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(Double sellingRate) {
        this.sellingRate = sellingRate;
    }

    public Double getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(Double purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    public String getConsumeReportUnit() {
        return consumeReportUnit;
    }

    public void setConsumeReportUnit(String consumeReportUnit) {
        this.consumeReportUnit = consumeReportUnit;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Double getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(Double currentQty) {
        this.currentQty = currentQty;
    }



    
    
    
}
