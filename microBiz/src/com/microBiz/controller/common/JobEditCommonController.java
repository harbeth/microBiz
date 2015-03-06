package com.microBiz.controller.common;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Job;
import com.microBiz.service.JobService;
import com.microBiz.service.MiUserService;
import com.microBiz.service.ProductService;

// only for new
public abstract class JobEditCommonController extends BaseController {

    protected JobService jobService;
    protected MiUserService userService;
    protected ProductService productService;
    
    public JobEditCommonController(){
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
        requestScope("jobStatus", jobStatus);
        return forward(getReturnJsp());
    }
    
    public abstract String getReturnJsp();
}
