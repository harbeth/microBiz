package com.microBiz.model;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.microBiz.MicroBizUtil;


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
    
    @Attribute(unindexed = true)
    private Double otherExpense;
    
    // jobCount didnot include canceled job
    @Attribute(unindexed = true)
    private Integer jobCount;
    
    @Attribute(unindexed = true)
    private Integer completeJobCount;
    
    

    public Integer getOnGoingJobCount(){
        return jobCount-completeJobCount;
    }
    
    public Double getProfitMargin(){
        return MicroBizUtil.roundTo2Demcial(100*((total-labourCost-materialCost-otherExpense)/total));
    }

    public InvoiceReport(){
        labourHrs = new Double(0);
        labourCost = new Double(0);
        materialCost = new Double(0);
        total = new Double(0);
        pymtReceived = new Double(0);
        otherExpense = new Double(0);
        jobCount = new Integer(0);
        completeJobCount = new Integer(0);
        
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
    
    public void addPymtReceived(Double d){
        pymtReceived = pymtReceived+d;
    }
    
    public void addOtherExpense(Double d){
        otherExpense = otherExpense+d;
    }


    public Double getLabourHrs() {
        return labourHrs;
    }
    
    public void increaseCompleteJobCount(){
        completeJobCount = completeJobCount+1;
    }
    
    public void decreaseJobCount(){
        jobCount = jobCount-1;
    }
    
    public void increaseJobCount(){
        jobCount = jobCount+1;
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
        return new Double(MicroBizUtil.roundTo2Demcial(materialCost.doubleValue()));
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


    public Double getOtherExpense() {
        return otherExpense;
    }

    public void setOtherExpense(Double otherExpense) {
        this.otherExpense = otherExpense;
    }


    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

    public Integer getCompleteJobCount() {
        return completeJobCount;
    }

    public void setCompleteJobCount(Integer completeJobCount) {
        this.completeJobCount = completeJobCount;
    }


}
