package com.microBiz.model;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;


@Model(kind = "prd")
public class Product extends MiBaseModel {

    private static final long serialVersionUID = 1L;

    // selling, only displayed for quotation and invoice, raw material, only displayed for job report, both
    private Integer type;
    
    @Attribute(unindexed = true)
    private String desc;
    
    private String model;

    
    @Attribute(unindexed = true)
    private Integer unit;
    
    @Attribute(unindexed = true)
    private Double rate;
    

    @Attribute(unindexed = true)
    private Double currentQty;
    
   
    @Attribute(unindexed = true)
    private Integer consumeReportUnit;
    
    private String active;
    
    private ModelRef<Supplier> supplierRef = new ModelRef<Supplier>(Supplier.class);

    // many to one, will set the list when retrieve product from datastore, eager loading
    @Attribute(persistent = false)
    private List<PrdRatio> prdRatioList;
    
    public Product(){
        active="on";
    }
 
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

 
    
 
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Integer getType() {
        return type;
    }
    
    public String getTypeLable() {
        return getLable(type);
    }

    public void setType(Integer type) {
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



    public Integer getConsumeReportUnit() {
        return consumeReportUnit;
    }
    
    public String getConsumeReportUnitLable() {
        return getLable(consumeReportUnit);
    }

    public void setConsumeReportUnit(Integer consumeReportUnit) {
        this.consumeReportUnit = consumeReportUnit;
    }


    public Double getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(Double currentQty) {
        this.currentQty = currentQty;
    }

    public Integer getUnit() {
        return unit;
    }
    
    public String getUnitLable() {
        return getLable(unit);
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public ModelRef<Supplier> getSupplierRef() {
        return supplierRef;
    }

    
    
}
