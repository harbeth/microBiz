package com.microBiz.service;

import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizConst;
import com.microBiz.MicroBizUtil;
import com.microBiz.meta.JobMeta;
import com.microBiz.meta.JobReportMeta;
import com.microBiz.model.InventoryChange;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceReport;
import com.microBiz.model.Job;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.JobReport;
import com.microBiz.model.MiUser;
import com.microBiz.model.PrdRatio;
import com.microBiz.model.Product;



public class JobService {
    
    private JobMeta job = new JobMeta();
    private JobReportMeta jobReport = new JobReportMeta();
    
    public List<Job> getAll() {
        return Datastore.query(job).sort(job.startingDate.desc).asList();
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
            
            if(job.getStatus().intValue()!= MicroBizConst.CODE_STATUS_CANCELED.intValue()){
                
                ir.increaseJobCount();
            }
        }else{

            if(job.getStatus().intValue()== MicroBizConst.CODE_STATUS_COMPLETED.intValue()){
                ir.increaseCompleteJobCount();
           }
            
            if(job.getStatus().intValue()== MicroBizConst.CODE_STATUS_CANCELED.intValue()){
                ir.decreaseJobCount();
            }
        }
        Datastore.put(job);
        Datastore.put(ir);
        
        
        
       
    }

    public List<Job> getAllUncompleteJobs() {
        return Datastore.query(job)
                .filter(job.status.equal(MicroBizConst.CODE_STATUS_OPEN))
                .sort(job.startingDate.desc).asList();
    
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
        result = Datastore.put(jobReport);
        if(jobReport.getStatus().intValue() == MicroBizConst.CODE_STATUS_APPROVED.intValue()){
            updateInventoryForApprovedJobReort(jobReport);
            updateInvoiceReport(jobReport);
        }
        return result;
    }
    
    private void updateInvoiceReport(JobReport jr){
        
        Job job = jr.getJobRef().getModel();
        Invoice invoice = job.getInvoiceRef().getModel();
        InvoiceReport ir = invoice.getInvoiceReportRef().getModel(); 

        Double labourHrs = new Double(0);
        Double materialCost = new Double(0);

        MiUserService userService = new MiUserService();                
        MiUser installer = userService.getUserByName(job.getInstaller());
  
        labourHrs = labourHrs+jr.getTravelHours()+jr.getWorkingHours();
        List<JobMaterialReport> jmrs = jr.getJobMaterialReportListRef().getModelList();
        Iterator<JobMaterialReport>  ii = jmrs.iterator();
        while(ii.hasNext()){
            JobMaterialReport jmr = ii.next();
            materialCost = materialCost + jmr.getQty()*(jmr.getProductRef().getModel().getRate())
                    *(jmr.getPrdRatioRef().getModel().getRatio());
        }
         
     
        ir.addLabourHrs(labourHrs);
        ir.addMaterialCost(materialCost);
        ir.addLabourCost(labourHrs*installer.getRate());
        Datastore.put(ir);
    }

    private void updateInventoryForApprovedJobReort(JobReport jr){
        List<JobMaterialReport> jmrs = jr.getJobMaterialReportListRef().getModelList();
        Iterator<JobMaterialReport> i = jmrs.iterator();
        ProductService productService = new ProductService();
        while(i.hasNext()){
            JobMaterialReport jmr = i.next();
            Product prd = jmr.getProductRef().getModel();
            Double reportQty = jmr.getQty();
            Double changedQty = null;
            if(jmr.getPrdRatioRef()!=null && jmr.getPrdRatioRef().getModel()!=null){
                PrdRatio pr = jmr.getPrdRatioRef().getModel();
                changedQty = reportQty*pr.getRatio()*(-1);
            }else{
                if(prd.getPrdRatioList()!=null && prd.getPrdRatioList().size()==1){//only one PrdRatio
                    changedQty = reportQty*prd.getPrdRatioList().get(0).getRatio()*(-1);
                }else{
                    changedQty = reportQty*(-1);
                }
            }
            InventoryChange ic = new InventoryChange();
            
            ic.setChangeQty(MicroBizUtil.roundTo2Demcial(changedQty));
            
            ic.setOriginalQty(prd.getCurrentQty());
            Double newQty = prd.getCurrentQty() + changedQty;
            newQty = MicroBizUtil.roundTo2Demcial(newQty);
            ic.setNewQty(newQty);
            ic.setNotes("descrease by invoice " + jr.getJobRef().getModel().getInvoiceRef().getModel().getInvoiceNumber());
            prd.setCurrentQty(newQty);
            productService.save(prd, ic);
        }
    }
    public void saveJobMaterialReports(List<JobMaterialReport> jmrList) {
        Datastore.put(jmrList);    
    }
    
    //later, will add installer name in the parameter
    public List<Job> getJobsForJobReport() {
        List<Job> jobs = Datastore.query(job).filter(job.status.equal(MicroBizConst.CODE_STATUS_OPEN)).asList();
        return populateJobReport(jobs);
    }

    public List<Job> getJobsForJobReport(String installerName) {
        List<Job> jobs = Datastore.query(job)
                .filter(job.status.equal(MicroBizConst.CODE_STATUS_OPEN), job.installer.equal(installerName))
                .sort(job.startingDate.desc)
                .asList();
        return populateJobReport(jobs);
    }
    
    private List<Job> populateJobReport(List<Job> jobs){
        Iterator<Job> i = jobs.iterator();
        while(i.hasNext()){
            Job job = i.next();
            List<JobReport> jrL = job.getJobReportListRef().getModelList();
            Iterator<JobReport> jri = jrL.iterator();
            while(jri.hasNext()){
                JobReport jr = jri.next();
                if(jr.getStatus().intValue() == MicroBizConst.CODE_STATUS_NEW.intValue()){
                    job.addNewJobReports(jr);
                }
            }
        }
        return jobs;
    }
    


}
