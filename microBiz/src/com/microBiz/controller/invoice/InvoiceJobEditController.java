package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Job;
import com.microBiz.service.JobService;
import com.microBiz.service.MiUserService;
import com.microBiz.service.ProductService;

// from the link to refresh whole page

public class InvoiceJobEditController extends BaseController{

    private JobService jobService;
    private MiUserService userService;
    private ProductService productService;
    
    public InvoiceJobEditController(){
        super();
        jobService = new JobService();
        userService = new MiUserService();
        productService = new ProductService();
    }
    
    // assign job, create new job or edit job
    
    @Override
    public Navigation run() throws Exception {
        String invoiceKey = asString("invoiceKey");
        String jobKey = asString("jobKey");
        Job job = null;
        if ( "-1".equals(jobKey) ) {
            // new
            job = new Job();
        }else{
            // edit
            job = jobService.get(asKey("jobKey"));
        }
        job.setInvoiceKey(invoiceKey);
        BeanUtil.copy(job, request);
        requestScope("installers", userService.getInstallers());
        requestScope("prds", productService.getReportingPrds());
        
        requestScope("paymentTypes", paymentTypes);
        return forward("invoice-job-edit.jsp");
    }
}
