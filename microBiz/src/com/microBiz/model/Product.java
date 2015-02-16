package com.microBiz.model;
import java.io.Serializable;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;


@Model(kind = "prd")
public class Product extends MiBaseModel {

  
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

    // many to one, will set the list when retrieve product from datastore, eager loading
    @Attribute(persistent = false)
    private List<PrdRatio> prdRatioList;
 

    
    public List<PrdRatio> getPrdRatioList() {
        return prdRatioList;
    }

    public void setPrdRatioList(List<PrdRatio> prdRatioList) {
        this.prdRatioList = prdRatioList;
    }

    @Attribute(unindexed = true)
    private String active;
    
 
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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


    public Double getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(Double currentQty) {
        this.currentQty = currentQty;
    }



    
    
    
}
