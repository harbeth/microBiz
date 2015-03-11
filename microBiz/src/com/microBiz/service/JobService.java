package com.microBiz.service;

import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.MicroBizConst;
import com.microBiz.meta.JobMeta;
import com.microBiz.meta.JobReportMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceReport;
import com.microBiz.model.Job;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.JobReport;
import com.microBiz.model.MiUser;



public class JobService {
    
    private JobMeta job = new JobMeta();
    private JobReportMeta jobReport = new JobReportMeta();
    
    public List<Job> getAll() {
        return Datastore.query(job).asList();
    }
    
    public Job get(Key key) {
        return Datastore.get(job, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }
        
    public void save(Job job) {
        
        Invoice invoice = job.getInvoiceRef().getModel();
        InvoiceReport ir = invoice.getInvoiceReportRef().getModel(); 
        if(job.getKey()==null){//new job
            
            if(job.getStatus().intValue()== MicroBizConst.CODE_STATUS_OPEN.intValue()){
                
                ir.increaseJobCount();
            }
        }else{

            if(job.getStatus().intValue()== MicroBizConst.CODE_STATUS_COMPLETED.intValue()){
                ir.increaseCompleteJobCount();
                Double labourHrs = new Double(0);
                //Double labourCost = new Double(0);
                Double materialCost = new Double(0);
                List<JobReport> jrs = job.getJobReportListRef().getModelList();
                Iterator<JobReport> i = jrs.iterator();
                MiUserService userService = new MiUserService();                
                MiUser installer = userService.getUserByName(job.getInstaller());
                while(i.hasNext()){
                    JobReport jr = i.next();
                    if(jr.getStatus().intValue()== MicroBizConst.CODE_STATUS_APPROVED.intValue()){
                        labourHrs = labourHrs+jr.getTravelHours()+jr.getWorkingHours();
                        List<JobMaterialReport> jmrs = jr.getJobMaterialReportListRef().getModelList();
                        Iterator<JobMaterialReport>  ii = jmrs.iterator();
                        while(ii.hasNext()){
                            JobMaterialReport jmr = ii.next();
                            materialCost = materialCost + jmr.getQty()*(jmr.getProductRef().getModel().getRate())
                                    *(jmr.getPrdRatioRef().getModel().getRatio());
                        }
                    }
                }
                ir.addLabourHrs(labourHrs);
                ir.addMaterialCost(materialCost);
                ir.addLabourCost(labourHrs*installer.getRate());
            
            
            }
            
            if(job.getStatus().intValue()== MicroBizConst.CODE_STATUS_CANCELED.intValue()){
                ir.decreaseJobCount();
            }
        }
        Datastore.put(job);
        Datastore.put(ir);
        
        
        
       
    }

    public List<Job> getAllUncompleteJobs() {
        return Datastore.query(job).filter(job.status.equal(MicroBizConst.CODE_STATUS_OPEN)).asList();
    
    }
    
    public List<JobReport> getNewJobReports() {
        
        return Datastore.query(jobReport).filter(jobReport.status.equal(MicroBizConst.CODE_STATUS_NEW)).asList();
    
    }
    
    public JobReport getJobReport(Key jobReportKey) {
        return Datastore.get(JobReport.class, jobReportKey);
    }
    
    public List<JobMaterialReport> getJobMaterialReport(Key jobReportKey){
        return Datastore.query(JobMaterialReport.class, jobReportKey).asList();
    }


    public Key saveJobReport(JobReport jobReport){
        Key result = null;
        Transaction tx = Datastore.beginTransaction();
        result = Datastore.put(tx, jobReport);
        tx.commit();
        return result;
    }

    public void saveJobMaterialReports(List<JobMaterialReport> jmrList) {
        Datastore.put(jmrList);    
    }
    
    //later, will add installer name in the parameter
    public List<Job> getJobsForJobReport() {
        List<Job> jobs = Datastore.query(job).filter(job.status.equal(MicroBizConst.CODE_STATUS_OPEN)).asList();
        Iterator i = jobs.iterator();
        while(i.hasNext()){
            Job job = (Job)i.next();
            List<JobReport> jrL = job.getJobReportListRef().getModelList();
            Iterator jri = jrL.iterator();
            while(jri.hasNext()){
                JobReport jr = (JobReport)jri.next();
                if(jr.getStatus().intValue() == MicroBizConst.CODE_STATUS_NEW.intValue()){
                    job.addNewJobReports(jr);
                }
            }
        }
        return jobs;
    }


}
