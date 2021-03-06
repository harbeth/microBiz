package com.microBiz.model;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.Sort;

import com.google.appengine.api.datastore.Query.SortDirection;
import com.microBiz.MicroBizConst;
import com.microBiz.MicroBizUtil;

@Model(kind = "job_report")
public class JobReport extends MiCreatorBaseModel {
    
    private static final long serialVersionUID = 1L;

    //new, installer entered, not verified by manager, can be edited
    // void, manager modify a job report entered by installer, the original job report become void, a void job report can not be edited
    // verified, only verified job report is used to caculate cost
    private Integer status;
    
    private Date workingDate;
    
    @Attribute(persistent = false)
    private String workingDateStr;

    @Attribute(unindexed = true)
    private String note;

    @Attribute(unindexed = true)
    private Double travelHours;

    @Attribute(unindexed = true)
    private Double workingHours;
    
    private ModelRef<Job> jobRef = new ModelRef<Job>(Job.class);

    @Attribute(persistent = false)
    private InverseModelListRef<JobMaterialReport, JobReport> jobMaterialReportListRef = 
    new InverseModelListRef<JobMaterialReport, JobReport>(JobMaterialReport.class, "jobReportRef", this,new Sort("count", SortDirection.ASCENDING));
 
    public JobReport(){
        status = MicroBizConst.CODE_STATUS_NEW;
    }
    
    public InverseModelListRef<JobMaterialReport, JobReport> getJobMaterialReportListRef() {
        return jobMaterialReportListRef;
    }

    public String getMaterialReportStr(){
        List<JobMaterialReport> jmrList = jobMaterialReportListRef.getModelList();
        StringBuffer result = new StringBuffer();
        Iterator<JobMaterialReport> i = jmrList.iterator();
        while(i.hasNext()){
            JobMaterialReport jmr = i.next();
            Product p = jmr.getProductRef().getModel();

            result.append(p.getModel())
            .append(": ").append(jmr.getQty())
            .append("  ").append(p.getConsumeReportUnitLable());
            if(jmr.getRatioDesc()!=null){
                result.append("  by  ").append(jmr.getRatioDesc());
            }
            result.append("  ----  ");

            
        }
        if(result.lastIndexOf("  ----  ")>0 &&result.length()>result.lastIndexOf("  ----  ")){
            result.delete(result.lastIndexOf("  ----  "), result.length());
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



    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }



    public Integer getStatus() {
        return status;
    }
    
    public String getStatusLable() {
        return getLable(status);
    }


    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(Date workingDate) {
        this.workingDate = workingDate;
    }

    public String getWorkingDateStr() {
        return MicroBizUtil.parseDateToStr(workingDate);
    }

    public void setWorkingDateStr(String workingDateStr) {
        this.workingDateStr = workingDateStr;
    }
    // set date from dateStr
    public void setWorkingDate() {
        this.workingDate = MicroBizUtil.parseStrToDate(workingDateStr);
    }


}
