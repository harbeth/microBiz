package com.microBiz.controller;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.microBiz.meta.JobMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.Job;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.JobService;

public class JobActionController extends BaseController{

    private InvoiceService invoiceService;
    private JobService jobService;
    
    private JobMeta metaJob;
    
    public JobActionController(){
        super();
        invoiceService = new InvoiceService();
        jobService = new JobService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // get invoice key for new
        Invoice invoice = invoiceService.get(asKey("invoiceKey"));

        Job job = null;
        /*
        // now only for new
        if(asKey(metaJob.key)!= null){ 
            // update
            job = jobService.get(asKey(metaJob.key));
            BeanUtil.copy(request, job); 
            
            if(asString("active")==null){
               // i.setActive("");
            }
        }else{ 
         */   
            // insert new
            job = new Job();
            BeanUtil.copy(request, job); 
            job.getInvoiceRef().setModel(invoice);
        
        jobService.save(job);
        
        requestScope("invoice", invoice);
        return forward("invoiceDetails.jsp");
    }
    
    
}
