package com.microBiz.model;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

//is child of repobReport
@Model(kind = "job_material_report")
public class JobMaterialReport extends MiBaseModel {

    private static final long serialVersionUID = 1L;

    private Double qty;
    
    //make sure JobMaterialReport for a job is as the same order if Job: usedPrdKey[]
    private Integer count;
 
    private ModelRef<Product> productRef = new ModelRef<Product>(Product.class);
    
    private ModelRef<PrdRatio> prdRatioRef = new ModelRef<PrdRatio>(PrdRatio.class);
    
    private ModelRef<JobReport> jobReportRef = new ModelRef<JobReport>(JobReport.class);
  
    


    public ModelRef<JobReport> getJobReportRef() {
        return jobReportRef;
    }




    public ModelRef<PrdRatio> getPrdRatioRef() {
        return prdRatioRef;
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


}
