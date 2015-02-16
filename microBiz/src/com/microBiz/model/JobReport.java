package com.microBiz.model;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.Sort;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.microBiz.MicroBizUtil;

import org.apache.commons.lang.StringEscapeUtils;


@Model(kind = "job_report")
public class JobReport implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
      
    private Date reportDate;

    
    //new, installer entered, not verified by manager, can be edited
    // void, manager modify a job report entered by installer, the original job report become void, a void job report can not be edited
    // verified, only verified job report is used to caculate cost
    private String status;

    @Attribute(unindexed = true)
    private String note;

    private Double travelHours;

    private Double workingHours;
    
    private ModelRef<Job> jobRef = new ModelRef<Job>(Job.class);

    @Attribute(persistent = false)
    private InverseModelListRef<JobMaterialReport, JobReport> jobMaterialReportListRef = 
    new InverseModelListRef<JobMaterialReport, JobReport>(JobMaterialReport.class, "jobReportRef", this,new Sort("count", SortDirection.ASCENDING));
 
    
    public InverseModelListRef<JobMaterialReport, JobReport> getJobMaterialReportListRef() {
        return jobMaterialReportListRef;
    }

    public String getMaterialReportStr(){
        List<JobMaterialReport> jmrList = jobMaterialReportListRef.getModelList();
        StringBuffer result = new StringBuffer();
        Iterator i = jmrList.iterator();
        while(i.hasNext()){
            JobMaterialReport jmr = (JobMaterialReport)i.next();
            Product p = jmr.getProductRef().getModel();
            result.append(p.getModel())
            .append(": ").append(jmr.getQty())
            .append("  ").append(p.getConsumeReportUnit())
            .append("  by  ").append(jmr.getPrdRatioRef().getModel().getDesc())
            .append(" * ");

            
        }
        return result.toString();
    
        
    }

    public ModelRef<Job> getJobRef() {
        return jobRef;
    }


    public Double getTravelHours() {
        return travelHours;
    }


    public void setTravelHours(Double travelHours) {
        this.travelHours = travelHours;
    }


    public Double getWorkingHours() {
        return workingHours;
    }


    public void setWorkingHours(Double workingHours) {
        this.workingHours = workingHours;
    }


    public Date getReportDate() {
        return reportDate;
    }




    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;

    }


    public String getReportDateStr() {
        return MicroBizUtil.parseDateToStr(reportDate);
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }



    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
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
        JobReport other = (JobReport) obj;
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
