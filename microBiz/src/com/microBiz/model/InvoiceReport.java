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
    
    @Attribute(unindexed = true)
    private Double total;
    
    @Attribute(unindexed = true)
    private Double pymtReceived;
    
    private Double salesCommission;
    
    @Attribute(unindexed = true)
    private Double otherExpense;
    
    private Boolean paidOff;



    public InvoiceReport(){
        labourHrs = new Double(0);
        labourCost = new Double(0);
        materialCost = new Double(0);
        total = new Double(0);
        pymtReceived = new Double(0);
        salesCommission = new Double(0);
        otherExpense = new Double(0);
 
        
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPymtReceived() {
        return pymtReceived;
    }

    public void setPymtReceived(Double pymtReceived) {
        this.pymtReceived = pymtReceived;
    }

    public Double getSalesCommission() {
        return salesCommission;
    }

    public void setSalesCommission(Double salesCommission) {
        this.salesCommission = salesCommission;
    }

    public Double getOtherExpense() {
        return otherExpense;
    }

    public void setOtherExpense(Double otherExpense) {
        this.otherExpense = otherExpense;
    }

    public Boolean getPaidOff() {
        return paidOff;
    }

    public void setPaidOff(Boolean paidOff) {
        this.paidOff = paidOff;
    }


}
