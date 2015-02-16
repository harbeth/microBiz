package com.microBiz.controller.invoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.controller.BaseController;
import com.microBiz.meta.JobMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.Job;
import com.microBiz.model.MiUser;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.JobService;
import com.microBiz.service.MiUserService;


// from AJAX call, on the submit form of jab details form
public class InvoiceJobEditActionController extends BaseController{

    private JobService jobService;
    private InvoiceService invoiceService;
    private MiUserService userService;
    private JobMeta metaJob;
    
    public InvoiceJobEditActionController(){
        super();
        jobService = new JobService();
        invoiceService = new InvoiceService();
        userService = new MiUserService();
        metaJob = new JobMeta();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        Job job = new Job();
        if ( asKey(metaJob.key) != null ) {
            job = jobService.get(asKey(metaJob.key));
        }else{
            job = new Job();
        }

        BeanUtil.copy(request, job);
        Key invoiceKey = Datastore.stringToKey(job.getInvoiceKey());
        Invoice invoice = invoiceService.get(invoiceKey);
        job.setStartingDate();
        job.getInvoiceRef().setModel(invoice);
        MiUser installer = userService.get(Datastore.stringToKey(asString("installer")));
        job.getInstallerRef().setModel(installer);
        
        String[] prds = paramValues("prds");
        List<String> prdKeys = new ArrayList<String>(prds.length);
        Collections.addAll(prdKeys, prds);
        
        job.setUsePrdKeys(prdKeys);
        jobService.save(job);
        
        invoice = invoiceService.get(invoiceKey);
        requestScope("jobs", invoice.getJobListRef().getModelList()); 
        requestScope("invoice", invoice); 
        // whole page refresh
        return forward("invoice-jobs.jsp");
        // job and invoice relation ???? wrong
    }
}
