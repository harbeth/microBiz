package com.microBiz.controller.jobReport;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Job;
import com.microBiz.service.JobService;
import com.microBiz.service.ProductService;


public abstract class RoleBasedSetJobsCommonController extends BaseController {

    protected JobService jobService;
    protected ProductService productService;
    
    public RoleBasedSetJobsCommonController(){
        super();
        jobService = new JobService();
        productService = new ProductService();
    }
    
    
    public void setJobsForReportByRole() {
        // only get data for invoice list, not details
        List<Job> jobs;
        HttpSession session = request.getSession();
        String myRole = (String)session.getAttribute("myrole");
        if(myRole.equals("installer")){
            jobs = jobService.getJobsForJobReport((String)session.getAttribute("userName"));
        }else{
            jobs = jobService.getJobsForJobReport();
        }
        requestScope("jobs", jobs);
    }
}
