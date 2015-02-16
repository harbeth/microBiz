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
    private String unit;
    
    @Attribute(unindexed = true)
    private Double rate;
    

    @Attribute(unindexed = true)
    private Double currentQty;
    
   
    @Attribute(unindexed = true)
    private String consumeReportUnit;

    // many to one, will set the list when retrieve product from datastore, eager loading
    @Attribute(persistent = false)
    private List<PrdRatio> prdRatioList;
 
    public boolean getShowRatio(){
        if(prdRatioList!=null && prdRatioList.size()>1){
            return true;
        }else{
            return false;
        }
    }
    
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }



    
    
    
}
