package com.microBiz.controller.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.microBiz.MicroBizConst;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.model.Job;
import com.microBiz.model.Product;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.JobService;
import com.microBiz.service.MiUserService;
import com.microBiz.service.ProductService;

// only for new
public abstract class JobEditCommonController extends BaseController {

    protected JobService jobService;
    protected MiUserService userService;
    protected ProductService productService;
    protected InvoiceService invoiceService;
    
    public JobEditCommonController(){
        super();
        jobService = new JobService();
        userService = new MiUserService();
        productService = new ProductService();
        invoiceService = new InvoiceService();
    }
    
    // assign job, create new job or edit job
    
    @Override
    public Navigation run() throws Exception {
        String invoiceKey = asString("invoiceKey");
        Invoice invoice = invoiceService.get(asKey("invoiceKey"));
        List<Job> jobsOfInvoice = invoice.getJobListRef().getModelList();
        List<String> installerNames =  userService.getInstallerNames();
        Iterator<Job> i = jobsOfInvoice.iterator();
        while (i.hasNext()){
            Job j = (Job)i.next();
            if(j.getStatus().intValue() == MicroBizConst.CODE_STATUS_OPEN.intValue()){
                installerNames.remove(j.getInstaller());
                installerNames.removeAll(j.getHelperNames());
            }
        }
        String jobKey = asString("jobKey");
        Job job = null;
        
        List<Product> prdList = productService.getReportingPrds();
        if ( "-1".equals(jobKey) ) {
            // new
            job = new Job();
            //for new job, no prds are checked
            
        }else{
            // edit
            job = jobService.get(asKey("jobKey"));
            // add back already assigned installer when edit an exiting job
            installerNames.add(job.getInstaller());
            
            
            // checked the already selected products
            List<Product> checkedPrds = new ArrayList<Product>();
            Iterator<Product> ip = prdList.iterator();
            while(ip.hasNext()){
                Product p = ip.next();
                if(job.getUsePrdKeys().contains(Datastore.keyToString(p.getKey()))){
                    checkedPrds.add(p);
                    ip.remove();
                }
            }
            requestScope("checkedPrds",checkedPrds );
            requestScope("checkedHelpers",job.getHelperNames() );
        }
        job.setInvoiceKey(invoiceKey);
        BeanUtil.copy(job, request);
        requestScope("installers", installerNames);
        requestScope("prds",prdList );
        requestScope("jobStatus", jobStatus);
        requestScope("invoice", invoice);
        return forward(getReturnJsp());
    }
    
    public abstract String getReturnJsp();
}
