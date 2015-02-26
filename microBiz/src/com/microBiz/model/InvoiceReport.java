package com.microBiz.model;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;


@Model(kind = "invoice_report")
public class InvoiceReport extends MiBaseModel {
    
    private static final long serialVersionUID = 1L;

    @Attribute(unindexed = true)
    private Double labourHrs;
 
    @Attribute(unindexed = true)
    private Double labourCost;
    
    @Attribute(unindexed = true)
    private Double materialCost;



    public InvoiceReport(){
        labourHrs = new Double(0);
        labourCost = new Double(0);
        materialCost = new Double(0);
 
        
    }
    
    public void addLabourHrs(Double d){
        labourHrs = labourHrs+d;
    }
    
    public void addLabourCost(Double d){
        labourCost = labourCost+d;
    }
    

    
    public void addMaterialCost(Double d){
        materialCost = materialCost+d;
    }


    public Double getLabourHrs() {
        return labourHrs;
    }

    public void setLabourHrs(Double labourHrs) {
        this.labourHrs = labourHrs;
    }

    public Double getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(Double labourCost) {
        this.labourCost = labourCost;
    }

    public Double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(Double materialCost) {
        this.materialCost = materialCost;
    }


}
