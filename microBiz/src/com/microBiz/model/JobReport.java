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
public class JobReport extends MiBaseModel {
    
   
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
            .append("  ").append(p.getConsumeReportUnit());
            if(jmr.getPrdRatioRef()!=null && jmr.getPrdRatioRef().getKey()!=null){
                result.append("  by  ").append(jmr.getPrdRatioRef().getModel().getDesc());
            }
            result.append(" * ");

            
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


    public String getReportDateStr() {
        return MicroBizUtil.parseDateToStr(createdAt);
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



}
