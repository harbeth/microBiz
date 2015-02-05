package com.microBiz.model;
import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;


@Model(kind = "job_material_report")
public class JobMaterialReport implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
      
    private Date reportDate;
    
    // ratio for calculate maerial cost
    private Double prdRatio;
    
    private Double qty;
    
    @Attribute(persistent = false)
    private String reportDateStr;

    private String note;
    // many to one
    private ModelRef<Job> jobRef = new ModelRef<Job>(Job.class);
    
    private ModelRef<Product> productRef = new ModelRef<Product>(Product.class);
  
    public ModelRef<Job> getJobRef() {
        return jobRef;
    }
    


    public Double getPrdRatio() {
        return prdRatio;
    }



    public void setPrdRatio(Double prdRatio) {
        this.prdRatio = prdRatio;
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


    public Date getReportDate() {
        return reportDate;
    }




    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }



    public String getReportDateStr() {
        return reportDateStr;
    }




    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
    }




    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        JobMaterialReport other = (JobMaterialReport) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
