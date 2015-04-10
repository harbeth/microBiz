package com.microBiz.model;

import java.util.ArrayList;
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

@Model(kind = "job")
public class Job extends MiCreatorBaseModel {

    private static final long serialVersionUID = 1L;

    @Attribute(persistent = false)
    private String startingDateStr;

    private Date startingDate;

    @Attribute(unindexed = true)
    private String arrivalTime;

    @Attribute(unindexed = true)
    private String note;

    @Attribute(unindexed = true)
    private List<String> usePrdKeys;
    
    @Attribute(unindexed = true)
    private List<String> helperNames;

    // ongoing, complete, canceled
    private Integer status;
    // only for display
    @Attribute(persistent = false)
    private String invoiceKey;
    
    // only for installer to edit new job report
    @Attribute(persistent = false)
    private List<JobReport> newJobReports;

    // many to one
    private ModelRef<Invoice> invoiceRef = new ModelRef<Invoice>(Invoice.class);

    private String installer;

    @Attribute(persistent = false)
    private InverseModelListRef<JobReport, Job> jobReportListRef =
        new InverseModelListRef<JobReport, Job>(JobReport.class, "jobRef", this,new Sort("createdAt", SortDirection.DESCENDING));
  

    public Job() {
        status = MicroBizConst.CODE_STATUS_OPEN;
    }

    public InverseModelListRef<JobReport, Job> getJobReportListRef() {
        return jobReportListRef;
    }

    public ModelRef<Invoice> getInvoiceRef() {
        return invoiceRef;
    }
    
    public boolean getNotCompleteCancelable(){

        List<JobReport> jrList = jobReportListRef.getModelList();
       
        Iterator<JobReport> i = jrList.iterator();
        while(i.hasNext()){
            JobReport jr = (JobReport)i.next();
            if(jr.getStatus().intValue() == MicroBizConst.CODE_STATUS_NEW){
                return true;
            }
        }
        return false;
        
    }
    //if there is new or approved job report, installer and prd used for the job can not be changed
    public boolean getInstallerPrdsChangable(){
        boolean result = true;
        List<JobReport> jrList = jobReportListRef.getModelList();
        Iterator<JobReport> i = jrList.iterator();
        while(i.hasNext() && result){
            JobReport jr = (JobReport)i.next();
            if(jr.getStatus().intValue() != MicroBizConst.CODE_STATUS_VOID.intValue()){//new or approved
                return false;
            }
        }
        return result;
        
    }
    
    public boolean getEditable(){
        
        if(status.intValue() == MicroBizConst.CODE_STATUS_OPEN){
            return true;          
        }
        return false;
        
    }

    // display date string from date
    public String getStartingDateStr() {
        return MicroBizUtil.parseDateToStr(startingDate);
    }

    public void setStartingDateStr(String startingDateStr) {
        this.startingDateStr = startingDateStr;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    // set date from dateStr
    public void setStartingDate() {
        this.startingDate = MicroBizUtil.parseStrToDate(startingDateStr);
    }


    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installerName) {
        this.installer = installerName;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    public String getInvoiceKey() {
        return invoiceKey;
    }

    public void setInvoiceKey(String invoiceKey) {
        this.invoiceKey = invoiceKey;
    }

    public List<String> getUsePrdKeys() {
        return usePrdKeys;
    }

    public void setUsePrdKeys(List<String> usePrdKeys) {
        this.usePrdKeys = usePrdKeys;
    }

    public List<JobReport> getNewJobReports() {
        return newJobReports;
    }

    public void addNewJobReports(JobReport newJobReport) {
        if(newJobReports ==null){
            newJobReports = new ArrayList<JobReport>();
        }
        newJobReports.add(newJobReport);
    }

    public List<String> getHelperNames() {
        return helperNames;
    }
    
    public String getHelperNamesStr() {
        String result = "";
        if (helperNames!=null){
            for(String name:helperNames){
                result = result  + name + "  ";
            }
        }
        return result;
    }

    public void setHelperNames(List<String> helperNames) {
        this.helperNames = helperNames;
    }


}
