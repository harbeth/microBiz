package com.microBiz.model;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

//is child of repobReport
@Model(kind = "job_material_report")
public class JobMaterialReport extends MiBaseModel {

    private static final long serialVersionUID = 1L;

    @Attribute(unindexed = true)
    private Double qty;
    
    @Attribute(unindexed = true)
    private Double ratioRate;
    
    @Attribute(unindexed = true)
    private String ratioDesc;
    
    //make sure JobMaterialReport for a job is as the same order if Job: usedPrdKey[]
    private Integer count;
 
    private ModelRef<Product> productRef = new ModelRef<Product>(Product.class);
    
    private ModelRef<JobReport> jobReportRef = new ModelRef<JobReport>(JobReport.class);
  
    


    public ModelRef<JobReport> getJobReportRef() {
        return jobReportRef;
    }


    public Double getQty() {
        return qty;
    }



    public void setQty(Double qty) {
        this.qty = qty;
    }



    public ModelRef<Product> getProductRef() {
        return productRef;
    }

    public Integer getCount() {
        return count;
    }




    public void setCount(Integer count) {
        this.count = count;
    }


    public Double getRatioRate() {
        return ratioRate;
    }


    public void setRatioRate(Double ratioRate) {
        this.ratioRate = ratioRate;
    }


    public String getRatioDesc() {
        return ratioDesc;
    }


    public void setRatioDesc(String ratioDesc) {
        this.ratioDesc = ratioDesc;
    }
    
    


}
